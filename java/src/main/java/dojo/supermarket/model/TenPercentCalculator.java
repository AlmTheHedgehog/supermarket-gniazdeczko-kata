package dojo.supermarket.model;

public class TenPercentCalculator implements DiscountCalculator{

    private static final int discountableGroupSize = 1;
    private final double argument;

    public TenPercentCalculator(double argument) {
        this.argument = argument;
    }

    @Override
    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        double discountAmount = calculateTotalDiscount(quantity, unitPrice, argument, discountableGroupSize);
        return new Discount(product, argument + "% off", discountAmount);
    }

    @Override
    public double calculateTotalDiscount(Quantity quantity, double unitPrice, double argument, int discountableGroupSize) {
        return -quantity.asDouble() * unitPrice * argument / 100.0;
    }

}
