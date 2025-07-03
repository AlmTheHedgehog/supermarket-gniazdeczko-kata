package dojo.supermarket.model;

public class Quantity {
    private final double quantity;

    public Quantity(double quantity) {
        this.quantity = quantity;
    }

    public int asInt() {
        return (int) quantity;  //TODO: this is wrong but business input is required here
    }

    public double asDouble() {
        return  quantity;
    }

    public Quantity increaseQuantity(Quantity additionalQuantity) {
        return new Quantity(this.quantity+additionalQuantity.asDouble());
    }
}
