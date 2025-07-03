package dojo.supermarket.model;

public class FiveForAmountCalculator implements DiscountCalculator {
    private static final int discountableGroupSize = 5;
    private final double argument;

    public FiveForAmountCalculator(double argument) {
        this.argument = argument;
    }

    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        return calculateTotalDiscount(product, quantity, unitPrice, argument, discountableGroupSize);
    }

}
