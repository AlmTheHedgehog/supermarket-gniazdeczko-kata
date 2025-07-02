package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Teller {

    private final Map<Product, Offer> offers = new HashMap<>(); // FIXME - might be a problem described in Product class in equals method. Better to use name. For this case it is OK enough

    public void addSpecialOffer(Product product, Offer offer) {
        offers.put(product, offer);
    }

    //DONT change interface
    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        receipt.addProducts(theCart.getProducts());
        theCart.handleOffers(offers, receipt);

        return receipt;
    }
}
