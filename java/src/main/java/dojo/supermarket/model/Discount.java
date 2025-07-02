package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;

public record Discount(Product product, String description, double discountAmount) {

}
