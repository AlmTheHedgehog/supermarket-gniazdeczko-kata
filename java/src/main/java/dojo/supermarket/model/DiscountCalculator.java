package dojo.supermarket.model;

public interface DiscountCalculator {

    public Discount calculate(Product p, Offer offer, Quantity quantity, double unitPrice);

}
