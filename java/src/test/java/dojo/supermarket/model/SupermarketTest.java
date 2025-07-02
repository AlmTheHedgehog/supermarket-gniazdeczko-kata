package dojo.supermarket.model;

import dojo.supermarket.model.offer.ForAmountOffer;
import dojo.supermarket.model.offer.PercentDiscountOffer;
import dojo.supermarket.model.offer.ThreeForTwoOffer;
import dojo.supermarket.model.product.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SupermarketTest {

    private static SupermarketCatalog catalog;
    private Teller teller;
    private ShoppingCart cart;

    static private final String TOOTHBRUSH = "toothbrush";
    static private final String APPLES = "apples";
    static private final String RICE = "rice";
    static private final String TOOTHPASTE = "toothpaste";
    static private final String CHERRY_TOMATOES = "cherryTomatoes";
    static private final String TAMAGOCHI = "tamagochi";

    @BeforeAll
    static void setUpBeforeClass() {
        catalog = new FakeCatalog();

        catalog.addProduct(Product.buildUnitProduct(TOOTHBRUSH, 0.99, 2));
        catalog.addProduct(Product.buildUnitProduct(TOOTHPASTE, 1.79, 20));
        catalog.addProduct(Product.buildUnitProduct(TAMAGOCHI, 2.12, 120));

        catalog.addProduct(Product.buildWeightProduct(APPLES, 1.99, 2.5));
        catalog.addProduct(Product.buildWeightProduct(RICE, 2.49, 250.4));
        catalog.addProduct(Product.buildWeightProduct(CHERRY_TOMATOES, 0.69, 325.1));
    }

    @BeforeEach
    void setUp() {
        teller = new Teller();
        cart = new ShoppingCart(catalog);
    }

    // Todo: test all kinds of discounts are applied properly

    @Test
    void tenPercentDiscountOnProductNotInCartTest() {
        //Catalog is not needed - redundant part (in prod it would be DB). You would usually use catalog to get products from it. Not build yourself
        //TODO like: There is 1 catalog with products. For each TC you take product from catalog, not create it yourself
        teller.addSpecialOffer(Product.buildWeightProduct("paste", 0.99), new PercentDiscountOffer(10));
        cart.addProduct("toothbrush", 2); //TODO - it would also possiple to do agregation - cart connected to the catalog. We do cart.addProduct(ProductName, quantity). And it is retrieved from the catalog
        
        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(1.98, receipt.getTotalPrice(), 0.01);
        assertEquals(Collections.emptyList(), receipt.getDiscounts());

    }

    @Test
    void getOneFreeTest(){
        var offerProduct = catalog.getSingleProduct("toothbrush");
        teller.addSpecialOffer(offerProduct, new ForAmountOffer(2, 0.99));
        cart.addProduct("toothbrush", 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(0.99, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void percentDiscountPerKiloTest() {
        var offerProduct = catalog.getSingleProduct(APPLES);
        teller.addSpecialOffer(offerProduct, new PercentDiscountOffer(20));
        cart.addProduct(APPLES, 2.5);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(3.98,  receipt.getTotalPrice(), 0.01);
    }

    @Test
    void percentDiscountPerUnitTest() {
        var offerProduct = catalog.getSingleProduct(RICE);
        teller.addSpecialOffer(offerProduct, new PercentDiscountOffer(10));
        cart.addProduct(RICE, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(4.48,  receipt.getTotalPrice(), 0.01);
    }

    @Test
    void fiveUnitDiscountTest(){
        var offerProduct = catalog.getSingleProduct(TOOTHPASTE);
        teller.addSpecialOffer(offerProduct, new ForAmountOffer(5, 7.49));
        cart.addProduct(TOOTHPASTE, 5);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(7.49,  receipt.getTotalPrice(), 0.01);
    }

    @Test
    void twoForAmountTest(){
        var offerProduct = catalog.getSingleProduct(CHERRY_TOMATOES);
        teller.addSpecialOffer(offerProduct, new ForAmountOffer(2, 0.99));
        cart.addProduct(CHERRY_TOMATOES, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(0.99, receipt.getTotalPrice(), 0.1);
    }

    @Test
    void threeForTwoTest(){
        var offerProduct = catalog.getSingleProduct(TAMAGOCHI);
        teller.addSpecialOffer(offerProduct, new ThreeForTwoOffer());
        cart.addProduct(TAMAGOCHI, 3);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(4.24, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void threeForTwoTakenTwoTest(){
        var offerProduct = catalog.getSingleProduct(TAMAGOCHI);
        teller.addSpecialOffer(offerProduct, new ThreeForTwoOffer());
        cart.addProduct(TAMAGOCHI, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(4.24, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void twoForOneAddSingleUnitTwiceTest(){
        var offerProduct = catalog.getSingleProduct(TOOTHBRUSH);
        teller.addSpecialOffer(offerProduct, new ForAmountOffer(2, 0.99));
        cart.addProduct(TOOTHBRUSH, 1);
        cart.addProduct(TOOTHBRUSH, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(0.99, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void nonIntegerQuantityWhenUnitQuantityTest(){
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(TOOTHBRUSH, 2.5));
    }

    @Test
    void notAllItemsInSpecialOffersTest(){
        var offerProduct = catalog.getSingleProduct(TOOTHBRUSH);
        teller.addSpecialOffer(offerProduct, new ForAmountOffer(2, 0.99));
        cart.addProduct(TOOTHBRUSH, 3);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(1.98, receipt.getTotalPrice(), 0.01);
    }

}
