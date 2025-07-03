package dojo.supermarket.model;

public class FiveForAmountCalculator implements DiscountCalculator {
    public Discount calculate(Product p, Offer offer, Quantity quantity, double unitPrice) {
        int discountableGroupSize = 5;
        int nrOfDiscountableGroups = quantity.asInt() / discountableGroupSize;
        double discountTotal = unitPrice * quantity.asDouble() - (offer.argument * nrOfDiscountableGroups + quantity.asInt() % 5 * unitPrice);
        return new Discount(p, discountableGroupSize + " for " + offer.argument, -discountTotal);
    }

}
