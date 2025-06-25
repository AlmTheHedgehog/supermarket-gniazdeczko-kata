package dojo.supermarket.model;

public class ProductQuantity {

    // FIXME - Speculative Generality. Class is unneeded. quantity can be moved to product
    // in code productQuantities fulfil exectly ProductQuantity role

    private final Product product;
    private final double quantity;

    public ProductQuantity(Product product, double weight) {
        this.product = product;
        this.quantity = weight;
    }

    public Product getProduct() {
        return product;
    }

    // FIXME - Message Chains. Any changes in these relationships require modifying the client
    public boolean productEquals(Object product){
        return this.product.equals(product);
    }

    public double getQuantity() {
        return quantity;
    }
}
