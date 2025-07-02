package dojo.supermarket.model;

public class ThreeForTwoCalculator implements DiscountCalculator {
    @Override
    public Discount calculate(Product p, Offer offer, int quantityAsInt, double unitPrice, double quantity) {
        return null;
    }

    @Override
    public Discount calculate(Product p, Offer offer, double unitPrice, double quantity) {
        return null;
    }

    public Discount calculate(Product p, int quantityAsInt, double unitPrice, double quantity) {
        int discountableGroupSize = 3;
        int nrOfDiscountableGroups = quantityAsInt / discountableGroupSize;
        double discountAmount = quantity * unitPrice - ((nrOfDiscountableGroups * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
        return new Discount(p, "3 for 2", -discountAmount);
    }
}
