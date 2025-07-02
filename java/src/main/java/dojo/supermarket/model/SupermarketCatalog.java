package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;

public interface SupermarketCatalog {

    void addProduct(Product product);

    Product getProduct(String productName, double quantity); //TODO - here we have a stock. Here we specify how much we want to take from stock

    Product getSingleProduct(String productName);
}
