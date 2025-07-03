package dojo.supermarket.model;

public class ThreeForTwoCalculator implements DiscountCalculator {

    public ThreeForTwoCalculator() {
    }

    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        int discountableGroupSize = 3;
        int nrOfDiscountableGroups = quantity.asInt() / discountableGroupSize;
        double discountAmount = quantity.asDouble() * unitPrice - ((nrOfDiscountableGroups * 2 * unitPrice) + quantity.asInt() % 3 * unitPrice);
        return new Discount(product, "3 for 2", -discountAmount);
    }
}
