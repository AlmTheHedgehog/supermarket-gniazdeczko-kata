package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;

import java.util.*;

public class Receipt {

    private final Map<Product, Product> items = new HashMap<>();
    private final List<Discount> discounts = new ArrayList<>();

    //should be integer
    public double getTotalPrice() {
        var costSum = items.keySet().stream().mapToDouble(Product::getTotalPrice).sum();
        var discountsNegativeSum = discounts.stream().mapToDouble(Discount::discountAmount).sum();
        return costSum +  discountsNegativeSum;
    }

    public void addProduct(Product product) {
        var cartProduct = Optional.ofNullable(items.getOrDefault(product, null));
        cartProduct.ifPresentOrElse(
                presentProduct -> presentProduct.addQuantity(product.getQuantity()),
                () -> items.put(product, product));
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void addProducts(Map<Product, Product> products) {
        products.keySet().forEach(this::addProduct);
    }

    //TODO printReceipt
}
