package dojo.supermarket.model;

public interface DiscountCalculator {

    Discount calculate(Product product, Quantity quantity, double unitPrice);

    default double calculateTotalDiscount(Quantity quantity, double unitPrice, double argument, int discountableGroupSize) {
        int nrOfDiscountableGroups = quantity.asInt() / discountableGroupSize;
        return unitPrice * quantity.asDouble() - (argument * nrOfDiscountableGroups + quantity.asInt() % nrOfDiscountableGroups * unitPrice);
    }
}
