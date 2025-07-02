package dojo.supermarket.model;

public class TenPercentCalculator implements DiscountCalculator{

    @Override
    public Discount calculate(Product p, Offer offer, int quantityAsInt, double unitPrice, double quantity) {
        return null;
    }

    @Override
    public Discount calculate(Product p, Offer offer, double unitPrice, double quantity) {
        return new Discount(p, offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
    }

    @Override
    public Discount calculate(Product p, int quantityAsInt, double unitPrice, double quantity) {
        return null;
    }

}
