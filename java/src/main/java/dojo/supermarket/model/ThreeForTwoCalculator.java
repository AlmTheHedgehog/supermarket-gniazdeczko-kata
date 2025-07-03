package dojo.supermarket.model;

public class ThreeForTwoCalculator implements DiscountCalculator {
    private static final int discountableGroupSize = 3;

    public ThreeForTwoCalculator() {
    }

    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        double argument = 2;
        double discountAmount = calculateTotalDiscount(quantity, unitPrice, argument, discountableGroupSize);
        return new Discount(product, "3 for 2", -discountAmount);
    }
}
