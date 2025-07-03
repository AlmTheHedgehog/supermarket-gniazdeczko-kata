package dojo.supermarket.model;

public class TenPercentCalculator implements DiscountCalculator{


    private final double argument;

    public TenPercentCalculator(double argument) {
        this.argument = argument;
    }

    @Override
    public Discount calculate(Product product, Quantity quantity, double unitPrice) {
        double discountAmount = -quantity.asDouble() * unitPrice * argument / 100.0;
        return new Discount(product, argument + "% off", discountAmount);
    }

}
