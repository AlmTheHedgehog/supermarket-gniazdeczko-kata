package dojo.supermarket.model;

public class FiveForAmountCalculator implements DiscountCalculator {
    private final double argument;

    public FiveForAmountCalculator(double argument) {
        this.argument = argument;
    }

    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        int discountableGroupSize = 5;
        int nrOfDiscountableGroups = quantity.asInt() / discountableGroupSize;
        double discountTotal = unitPrice * quantity.asDouble() - (argument * nrOfDiscountableGroups + quantity.asInt() % 5 * unitPrice);
        return new Discount(product, discountableGroupSize + " for " + argument, -discountTotal);
    }

}
