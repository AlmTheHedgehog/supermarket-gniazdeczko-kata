package dojo.supermarket.model;

public class TwoForAmountCalculator implements DiscountCalculator {

    @Override
    public Discount calculate(Product p, Offer offer, int quantityAsInt, double unitPrice, double quantity) {
            int discountableGroupSize = 2;
            int nrOfDiscountableGroups = quantityAsInt / discountableGroupSize;
            double total = offer.argument * nrOfDiscountableGroups + quantityAsInt % 2 * unitPrice;
            double discountN = unitPrice * quantity - total;
            return new Discount(p, "2 for " + offer.argument, -discountN);
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
