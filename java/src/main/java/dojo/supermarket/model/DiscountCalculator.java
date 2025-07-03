package dojo.supermarket.model;

public interface DiscountCalculator {

    Discount calculate(Product product, Quantity quantity, double unitPrice);

}
