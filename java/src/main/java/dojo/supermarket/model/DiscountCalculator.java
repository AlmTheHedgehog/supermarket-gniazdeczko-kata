package dojo.supermarket.model;

public interface DiscountCalculator {

    public Discount calculate(Product p, Offer offer, int quantityAsInt, double unitPrice, double quantity);

    public Discount calculate(Product p, Offer offer, double unitPrice, double quantity);

    public Discount calculate(Product p,  int quantityAsInt, double unitPrice, double quantity);
}
