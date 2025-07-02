package dojo.supermarket.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;


import static org.junit.jupiter.api.Assertions.assertEquals;

class SupermarketTest {

    // Todo: test all kinds of discounts are applied properly
    private static final Product TOOTHBRUSH = new Product("toothbrush", ProductUnit.EACH);
    private static final Product APPLES = new Product("apples", ProductUnit.KILO);
    private static final double APPLES_PRICE = 1.99;


    SupermarketCatalog catalog = new FakeCatalog();

    Teller teller = new Teller(catalog);

    @BeforeEach
    void setUp() {
        catalog.addProduct(TOOTHBRUSH, 0.99);
        catalog.addProduct(APPLES, APPLES_PRICE);
    }

    @Test
    void should_calculateTotalPrice() {
        //given
        double applesQuantity = 2.5;
        double expectedTotalPrice = applesQuantity*APPLES_PRICE;
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(APPLES, applesQuantity);

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
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(APPLES, applesQuantity);

        //when
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        //then
        assertEquals(1, receipt.getItems().size());
        assertEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(expectedTotalPrice, receipt.getTotalPrice(), 0.01);
    }


}
