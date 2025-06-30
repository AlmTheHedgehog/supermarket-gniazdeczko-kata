package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class ForAmountOffer implements Offer {
    
    private int quantityInBundle;
    private double bundlePrice;
    
    public ForAmountOffer(int quantityInBundle, double bundlePrice) {
        this.quantityInBundle = quantityInBundle;
        this.bundlePrice = bundlePrice;
    }
    
    
    @Override
    public Discount getDiscount(Product product, Double amountOfProduct, double productPrice) {
        int viableForDiscount = amountOfProduct.intValue()/quantityInBundle;
        double disAmount = (quantityInBundle*productPrice - bundlePrice)*viableForDiscount;
        return new Discount(product, quantityInBundle+" for " + bundlePrice, -disAmount);
    }
}
