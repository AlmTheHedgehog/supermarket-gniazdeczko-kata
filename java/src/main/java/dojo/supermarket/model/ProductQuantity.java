package dojo.supermarket.model;

public class ProductQuantity {

    private final Product product;
    private final double quantity;

    public ProductQuantity(Product product, double weight) {
        this.product = product;
        this.quantity = weight;
    }

    public Product getProduct() {
        return product;
    }

    public boolean productEquals(Object product){
        return this.product.equals(product);
    }

    public double getQuantity() {
        return quantity;
    }
}
