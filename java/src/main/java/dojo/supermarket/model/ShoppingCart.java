package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.product.Product;

import java.util.*;
import java.util.function.Supplier;

public class ShoppingCart {

    private final Map<Product, Product> products = new HashMap<>();
    private final SupermarketCatalog catalog;

    public ShoppingCart(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    private void addProduct(Product product) {
        var cartProduct = Optional.ofNullable(products.getOrDefault(product, null));
        cartProduct.ifPresentOrElse(
                        presentProduct -> presentProduct.addQuantity(product.getQuantity()),
                        () -> products.put(product, product));
    }

    public void addProduct(String productName, double quantity) {
        addProduct(catalog.getProduct(productName,  quantity));
    }

    public Map<Product, Product> getProducts() {
        return new HashMap<>(products);
    }

    //TODO refactor method
    void handleOffers(Map<Product, Offer> offers, Receipt receipt) {
        products.keySet()
                .stream()
                .filter(offers::containsKey)
                .map(product -> offers.get(product).getDiscount(product))
                .forEach(receipt::addDiscount);
    }

}
