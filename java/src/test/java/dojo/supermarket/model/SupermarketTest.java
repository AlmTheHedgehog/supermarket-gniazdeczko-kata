package dojo.supermarket.model;

import dojo.supermarket.model.offer.ForAmountOffer;
import dojo.supermarket.model.offer.PercentDiscountOffer;
import dojo.supermarket.model.offer.ThreeForTwoOffer;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SupermarketTest {

    // Todo: test all kinds of discounts are applied properly

    @Test
    void tenPercentDiscountOnProductNotInCartTest() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.UNIT);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.KILO);
        catalog.addProduct(apples, 1.99);

        Teller teller = new Teller(catalog);

        teller.addSpecialOffer(toothbrush, new PercentDiscountOffer(10));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);
        
        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(4.975, receipt.getTotalPrice(), 0.01);
        assertEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().getFirst();
        assertEquals(apples, receiptItem.getProduct());
        assertEquals(1.99, receiptItem.getPrice());
        assertEquals(2.5*1.99, receiptItem.getTotalPrice());
        assertEquals(2.5, receiptItem.getQuantity());

    }

    @Test
    void getOneFreeTest(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.UNIT);
        catalog.addProduct(toothbrush, 0.99);
        Teller teller = new Teller(catalog);

        teller.addSpecialOffer(toothbrush, new ForAmountOffer(2, 0.99));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(0.99, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void percentDiscountPerKiloTest() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product apples = new Product("apples", ProductUnit.KILO);
        catalog.addProduct(apples, 1.99);
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(apples, new PercentDiscountOffer(20));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(3.98,  receipt.getTotalPrice(), 0.01);
    }

    @Test
    void percentDiscountPerUnitTest() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product rice = new Product("rice", ProductUnit.UNIT);
        catalog.addProduct(rice, 2.49);
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(rice, new PercentDiscountOffer(10));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rice, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(4.48,  receipt.getTotalPrice(), 0.01);
    }

    @Test
    void fiveUnitDiscountTest(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothpaste = new Product("toothpaste", ProductUnit.UNIT);
        catalog.addProduct(toothpaste, 1.79);
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(toothpaste, new ForAmountOffer(5, 7.49));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothpaste, 5);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(7.49,  receipt.getTotalPrice(), 0.01);
    }

    @Test
    void twoForAmountTest(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product cherryTomatoes = new Product("cherryTomatoes", ProductUnit.UNIT);
        catalog.addProduct(cherryTomatoes, 0.69);
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(cherryTomatoes, new ForAmountOffer(2, 0.99));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(cherryTomatoes, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(0.99, receipt.getTotalPrice(), 0.1);
    }

    @Test
    void threeForTwoTest(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product tamagochi = new Product("tamagochi", ProductUnit.UNIT);
        catalog.addProduct(tamagochi, 2.12);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(tamagochi, new ThreeForTwoOffer());

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(tamagochi, 3);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(4.24, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void threeForTwoTakenTwoTest(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product tamagochi = new Product("tamagochi", ProductUnit.UNIT);
        catalog.addProduct(tamagochi, 2.12);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(tamagochi, new ThreeForTwoOffer());

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(tamagochi, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(4.24, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void twoForOneAddSingleUnitTwiceTest(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.UNIT);
        catalog.addProduct(toothbrush, 1.99);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(toothbrush, new ForAmountOffer(2, 0.99));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 1);
        cart.addItemQuantity(toothbrush, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(0.99, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void nonIntegerQuantityWhenUnitQuantityTest(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.UNIT);
        catalog.addProduct(toothbrush, 1.99);

        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> cart.addItemQuantity(toothbrush, 2.5));
    }

    @Test
    void notAllItemsInSpecialOffersTest(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.UNIT);
        catalog.addProduct(toothbrush, 1.99);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(toothbrush, new ForAmountOffer(2, 0.99));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(2.98, receipt.getTotalPrice(), 0.01);
    }

}
