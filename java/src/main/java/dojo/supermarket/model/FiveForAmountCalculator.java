package dojo.supermarket.model;

public class FiveForAmountCalculator implements DiscountCalculator {
    public Discount calculate(Product p, Offer offer, int quantityAsInt, double unitPrice, double quantity) {
        int discountableGroupSize = 5;
        int nrOfDiscountableGroups = quantityAsInt / discountableGroupSize;
        double discountTotal = unitPrice * quantity - (offer.argument * nrOfDiscountableGroups + quantityAsInt % 5 * unitPrice);
        return new Discount(p, discountableGroupSize + " for " + offer.argument, -discountTotal);
    }

    @Override
    public Discount calculate(Product p, Offer offer, double unitPrice, double quantity) {
        return null;
    }

    @Override
    public Discount calculate(Product p, int quantityAsInt, double unitPrice, double quantity) {
        return null;
    }
}
