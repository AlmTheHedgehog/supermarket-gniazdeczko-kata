package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;

import java.util.HashMap;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private final Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(Product product, Offer offer) {
        offers.put(product, offer);
    }

    //DONT change interface
    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        receipt.addProducts(theCart.productQuantities(), catalog);
        theCart.handleOffers(receipt, offers, catalog);

        return receipt;
    }
}
