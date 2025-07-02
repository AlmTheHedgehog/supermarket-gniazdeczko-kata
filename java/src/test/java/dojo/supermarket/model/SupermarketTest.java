package dojo.supermarket.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;


import static org.junit.jupiter.api.Assertions.assertEquals;

class SupermarketTest {

    private static final Product TOOTHBRUSH = new Product("toothbrush", ProductUnit.EACH);
    private static final Product APPLES = new Product("apples", ProductUnit.KILO);
    private static final double APPLES_PRICE = 1.99;
    private static final double TOOTHBRUSH_PRICE = 1.00;


    SupermarketCatalog catalog = new FakeCatalog();

    Teller teller = new Teller(catalog);

    @BeforeEach
    void setUp() {
        catalog.addProduct(TOOTHBRUSH, TOOTHBRUSH_PRICE);
        catalog.addProduct(APPLES, APPLES_PRICE);
    }

    @Test
    void should_calculateTotalPrice() {
        //given
        double applesQuantity = 2.5;
        double expectedTotalPrice = applesQuantity*APPLES_PRICE;
        ShoppingCart cart = aAppleCart(applesQuantity);

        //when
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        //then
        assertEquals(1, receipt.getItems().size());

        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(APPLES, receiptItem.getProduct());
        assertEquals(APPLES_PRICE, receiptItem.getPrice());
        assertEquals(expectedTotalPrice, receiptItem.getTotalPrice());
        assertEquals(applesQuantity, receiptItem.getQuantity());

        assertEquals(expectedTotalPrice, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void should_notApplyDiscountForNotDiscountedProduct() {
        //given
        double discount = 10.0;
        double applesQuantity = 2.5;
        double expectedTotalPrice = applesQuantity*APPLES_PRICE;
        teller.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, TOOTHBRUSH, discount);
        ShoppingCart cart = aAppleCart(applesQuantity);

        //when
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        //then
        assertEquals(1, receipt.getItems().size());
        assertEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(expectedTotalPrice, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void should_applyTenPercentDiscount() {
        //given
        double discountPercent = 10.0;
        double toothbrushQuantity = 1;
        double expectedProductPrice = TOOTHBRUSH_PRICE;
        double expectedTotalPrice = 0.9;
        teller.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, TOOTHBRUSH, discountPercent);
        ShoppingCart cart = aSingleToothbrushCart();

        //when
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        //then
        assertEquals(1, receipt.getItems().size());

        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(TOOTHBRUSH, receiptItem.getProduct());
        assertEquals(TOOTHBRUSH_PRICE, receiptItem.getPrice());
        assertEquals(expectedProductPrice, receiptItem.getTotalPrice());
        assertEquals(toothbrushQuantity, receiptItem.getQuantity());

        assertEquals(expectedTotalPrice, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void should_applyTwoForOneDiscount() {
        //given
        double toothbrushQuantity = 2;
        double expectedProductPrice = TOOTHBRUSH_PRICE;
        double expectedProductTotalPrice = 2.0;
        double expectedTotalPrice = 1.0;
        teller.addSpecialOffer(SpecialOfferType.TWO_FOR_AMOUNT, TOOTHBRUSH, TOOTHBRUSH_PRICE);
        ShoppingCart cart = twoToothbrushesCart();

        //when
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        //then
        assertEquals(1, receipt.getItems().size());

        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(TOOTHBRUSH, receiptItem.getProduct());
        assertEquals(TOOTHBRUSH_PRICE, receiptItem.getPrice());
        assertEquals(expectedProductTotalPrice, receiptItem.getTotalPrice());
        assertEquals(toothbrushQuantity, receiptItem.getQuantity());

        assertEquals(expectedTotalPrice, receipt.getTotalPrice(), 0.01);
    }



    private static ShoppingCart aAppleCart(double applesQuantity) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(APPLES, applesQuantity);
        return cart;
    }

    private static ShoppingCart aSingleToothbrushCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(TOOTHBRUSH, 1);
        return cart;
    }

    private static ShoppingCart twoToothbrushesCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(TOOTHBRUSH, 2);
        return cart;
    }


}
