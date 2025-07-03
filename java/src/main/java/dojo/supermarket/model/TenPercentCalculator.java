package dojo.supermarket.model;

public class TenPercentCalculator implements DiscountCalculator{


    @Override
    public Discount calculate(Product p, Offer offer, Quantity quantity, double unitPrice) {
        return new Discount(p, offer.argument + "% off", -quantity.asDouble() * unitPrice * offer.argument / 100.0);
    }

}
