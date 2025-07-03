package dojo.supermarket.model;

public class TwoForAmountCalculator implements DiscountCalculator {

    private final double argument;

    public TwoForAmountCalculator(double argument) {
        this.argument = argument;
    }

    @Override
    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
            int discountableGroupSize = 2;
            int nrOfDiscountableGroups = quantity.asInt() / discountableGroupSize;
            double total = argument * nrOfDiscountableGroups + quantity.asInt() % 2 * unitPrice;
            double discountN = unitPrice * quantity.asDouble() - total;
            return new Discount(product, "2 for " + argument, -discountN);
    }

}
