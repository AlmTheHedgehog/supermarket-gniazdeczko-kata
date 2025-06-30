package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class PercentDiscountOffer implements Offer {

    private final double discountFraction;
    private final int discountPercentage;

    public PercentDiscountOffer(int discountPercentage) {
        discountFraction = discountPercentage / 100.0;
        this.discountPercentage =  discountPercentage;
    }


    @Override
    public Discount getDiscount(Product product, Double amountOfProduct, double productPrice) {
        return new Discount(product, discountPercentage + "% off", -amountOfProduct * productPrice * discountFraction);

    }
}
