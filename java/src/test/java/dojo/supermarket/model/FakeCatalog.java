package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;

import java.util.HashMap;
import java.util.Map;

public class FakeCatalog implements SupermarketCatalog {
    private  Map<String, Product> products = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    @Override
    public Product getProduct(String productName, double quantity) {
        var product = Product.copyProduct(products.get(productName));
        product.setQuantity(quantity);
        return product;
    }

    @Override
    public Product getSingleProduct(String productName) {
        var product = Product.copyProduct(products.get(productName));
        product.setQuantity(1);
        return product;
    }


}
