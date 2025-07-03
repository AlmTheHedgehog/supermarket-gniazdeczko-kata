package dojo.supermarket.model;

public class ThreeForTwoCalculator implements DiscountCalculator {
    private static final int discountableGroupSize = 3;

    public ThreeForTwoCalculator() {
    }

    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        double argument = 2;
        return calculateTotalDiscount(product, quantity, unitPrice, argument, discountableGroupSize);
    }
}
