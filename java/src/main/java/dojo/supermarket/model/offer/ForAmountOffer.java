package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.product.Product;

public class ForAmountOffer implements Offer {
    
    private int quantityInBundle;
    private double bundlePrice;
    
    public ForAmountOffer(int quantityInBundle, double bundlePrice) {
        this.quantityInBundle = quantityInBundle;
        this.bundlePrice = bundlePrice;
    }
    
    
    @Override
    public Discount getDiscount(Product product) {
        int viableForDiscount = ((int)product.getQuantity())/quantityInBundle;
        double disAmount = (quantityInBundle* product.getPrice() - bundlePrice)*viableForDiscount;
        return new Discount(product, quantityInBundle + " for " + bundlePrice, -disAmount);
    }
}
