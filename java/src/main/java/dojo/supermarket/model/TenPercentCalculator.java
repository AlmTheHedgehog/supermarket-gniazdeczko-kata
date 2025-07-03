package dojo.supermarket.model;

public class TenPercentCalculator implements DiscountCalculator {

    private static final int discountableGroupSize = 1;
    private final double argument;

    public TenPercentCalculator(double argument) {
        this.argument = argument;
    }

    @Override
    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        return calculateTotalDiscount(product, quantity, unitPrice, argument, discountableGroupSize);
    }

    @Override
    public Discount calculateTotalDiscount(Product product, Quantity quantity, double unitPrice, double argument, int discountableGroupSize) {
        double discountAmount = -quantity.asDouble() * unitPrice * argument / 100.0;
        return new Discount(product, argument + "% off", discountAmount);
    }

}
