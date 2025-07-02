package dojo.supermarket.model.product;

import java.util.Objects;

//TODO - probably Product should be interface or abstract class. Then there is WeightProduct and UnitProduct. The problem is how to implement add general add quantity for Product?
public class Product {

    private final String name;
    private final ProductUnit unit;
    private double price;
    private double quantity;

    private Product(String name, ProductUnit unit, double price, double quantity) {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
    }

    static public Product buildWeightProduct(String name, double price, double quantity) {
        return new Product(name, ProductUnit.KILO, price, quantity);
    }

    static public Product buildWeightProduct(String name, double price) {
        return new Product(name, ProductUnit.KILO, price, 1);
    }

    static public Product buildUnitProduct(String name, double price, int quantity) {
        return new Product(name, ProductUnit.UNIT, price, quantity);
    }

    static public Product buildUnitProduct(String name, double price) {
        return new Product(name, ProductUnit.UNIT, price, 1);
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public void addQuantity(double quantity) {
        setQuantity(this.quantity + quantity);
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        if ((this.unit == ProductUnit.UNIT) && (quantity != (long) quantity)) {
            throw new IllegalArgumentException("""
                    "%s" product is counted in natural numbers
                    """.formatted(name));
        }
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        //FIXME - is used in containKey for Map(handleOffers method) - better to say that it equals only by name. But then there can be a product with different price in catalog and in shopingCart
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(unit, product.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit);
    }

    public static Product copyProduct(Product product) {
        return new Product(product.name, product.unit, product.price, product.quantity);
    }

}
