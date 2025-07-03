package dojo.supermarket.model;

public class TwoForAmountCalculator implements DiscountCalculator {

    static final int discountableGroupSize = 2;
    private final double argument;

    public TwoForAmountCalculator(double argument) {
        this.argument = argument;
    }

    @Override
    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        double discountAmount = calculateTotalDiscount(quantity, unitPrice, argument, discountableGroupSize);
        return new Discount(product, "2 for " + argument, -discountAmount);
    }

}
