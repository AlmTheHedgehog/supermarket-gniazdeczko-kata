package dojo.supermarket.model;

public class TwoForAmountCalculator implements DiscountCalculator {

    @Override
    public Discount calculate(Product p, Offer offer, Quantity quantity,  double unitPrice) {
            int discountableGroupSize = 2;
            int nrOfDiscountableGroups = quantity.asInt() / discountableGroupSize;
            double total = offer.argument * nrOfDiscountableGroups + quantity.asInt() % 2 * unitPrice;
            double discountN = unitPrice * quantity.asDouble() - total;
            return new Discount(p, "2 for " + offer.argument, -discountN);
    }

}
