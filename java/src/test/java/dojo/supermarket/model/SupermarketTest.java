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
    void should_notApplyDiscountForNotDiscountedProductWhenProductNotInCart() {
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
    void should_notApplyDiscountForNotDiscountedProductWhenProductInCart() {
        //given
        double discountPercent = 10.0;
        double toothbrushQuantity = 1;
        double applesQuantity = 2.5;
        double expectedDiscount = (toothbrushQuantity*TOOTHBRUSH_PRICE) *  discountPercent/100;
        double expectedTotalPrice = applesQuantity*APPLES_PRICE + (toothbrushQuantity*TOOTHBRUSH_PRICE)-expectedDiscount;
        teller.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, TOOTHBRUSH, discountPercent);

        ShoppingCart cart = aAppleCart(applesQuantity);
        cart.addItemQuantity(TOOTHBRUSH, toothbrushQuantity);

        //when
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        //then
        assertEquals(2, receipt.getItems().size());
        assertEquals(1, receipt.getDiscounts().size());
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
        ShoppingCart cart = toothbrushesCart(toothbrushQuantity);

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
        double expectedProductTotalPrice = 2.0;
        double expectedTotalPrice = 1.0;
        teller.addSpecialOffer(SpecialOfferType.TWO_FOR_AMOUNT, TOOTHBRUSH, TOOTHBRUSH_PRICE);
        ShoppingCart cart = toothbrushesCart(toothbrushQuantity);

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

    @Test
    void should_applyThreeForTwoDiscount() {
        //given
        double toothbrushQuantity = 3;
        double expectedProductTotalPrice = 3.0;
        double expectedTotalPrice = 2.0;

        int notUsed = 0;
        teller.addSpecialOffer(SpecialOfferType.THREE_FOR_TWO, TOOTHBRUSH, notUsed);
        ShoppingCart cart = toothbrushesCart(toothbrushQuantity);

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

    @Test
    void should_applyFiveForThreeDiscount() {
        //given
        double toothbrushQuantity = 5;
        double expectedProductTotalPrice = 5.0;
        double expectedTotalPrice = 3.0;
        teller.addSpecialOffer(SpecialOfferType.FIVE_FOR_AMOUNT, TOOTHBRUSH, 3*TOOTHBRUSH_PRICE);
        ShoppingCart cart = toothbrushesCart(toothbrushQuantity);

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

    @Test
    void should_applyOnlyTheLastDiscountForGivenProduct() {
        //given
        double toothbrushQuantity = 5;
        double expectedProductTotalPrice = 5.0;
        double expectedTotalPrice = 3.0;
        int notUsed = 0;
        teller.addSpecialOffer(SpecialOfferType.THREE_FOR_TWO, TOOTHBRUSH, notUsed);
        teller.addSpecialOffer(SpecialOfferType.FIVE_FOR_AMOUNT, TOOTHBRUSH, 3*TOOTHBRUSH_PRICE);

        ShoppingCart cart = toothbrushesCart(toothbrushQuantity);

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

    private static ShoppingCart toothbrushesCart(double quantity) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(TOOTHBRUSH, quantity);
        return cart;
    }
}
