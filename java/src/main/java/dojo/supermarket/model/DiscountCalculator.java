package dojo.supermarket.model;

public interface DiscountCalculator {

    Discount calculate(Product product, Quantity quantity, double unitPrice);

    default Discount calculateTotalDiscount(Product product, Quantity quantity, double unitPrice, double argument, int discountableGroupSize) {
        int nrOfDiscountableGroups = quantity.asInt() / discountableGroupSize;
        double discountAmount = unitPrice * quantity.asDouble() - (argument * nrOfDiscountableGroups + quantity.asInt() % nrOfDiscountableGroups * unitPrice);

        return new Discount(product, discountableGroupSize + " for " + argument, -discountAmount);
    }
}
