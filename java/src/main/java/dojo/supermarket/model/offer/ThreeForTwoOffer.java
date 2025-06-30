package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class ThreeForTwoOffer implements Offer {

    @Override
    public Discount getDiscount(Product product, Double amountOfProduct, double productPrice) {
        int viableForDiscount = amountOfProduct.intValue()/3;
        double discountAmount = viableForDiscount * productPrice;
        return new Discount(product, "3 for 2", -discountAmount);
    }
}
