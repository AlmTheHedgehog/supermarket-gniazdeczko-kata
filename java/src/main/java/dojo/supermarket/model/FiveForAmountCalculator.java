package dojo.supermarket.model;

public class FiveForAmountCalculator implements DiscountCalculator {
    private static final int discountableGroupSize = 5;
    private final double argument;

    public FiveForAmountCalculator(double argument) {
        this.argument = argument;
    }

    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        double discountAmount = calculateTotalDiscount(quantity, unitPrice, argument, discountableGroupSize);
        return new Discount(product, discountableGroupSize + " for " + argument, -discountAmount);
    }

}
