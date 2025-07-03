package dojo.supermarket.model;

public class TwoForAmountCalculator implements DiscountCalculator {

    static final int discountableGroupSize = 2;
    private final double argument;

    public TwoForAmountCalculator(double argument) {
        this.argument = argument;
    }

    @Override
    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        return calculateTotalDiscount(product, quantity, unitPrice, argument, discountableGroupSize);
    }

}
