package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;

import java.util.*;

public class ShoppingCart {

    private final Map<Product, Double> productQuantities = new HashMap<>();

    Map<Product, Double> productQuantities() {
        return Collections.unmodifiableMap(productQuantities);
    }

    public void addItemQuantity(Product product, double quantity) {
        if (!product.getUnit().equals(ProductUnit.KILO))
            throw new IllegalArgumentException("Product unit is not "+ProductUnit.KILO.name());
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    public void addItemQuantity(Product product, int quantity) {
        if (!product.getUnit().equals(ProductUnit.UNIT))
            throw new IllegalArgumentException("Product unit is not " + ProductUnit.UNIT.name());
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, (double) quantity);
        }
    }

    //TODO refactor method
    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        productQuantities.entrySet()
                .stream()
                .filter(productAndQuantity -> offers.containsKey(productAndQuantity.getKey()))
                .map(productAndQuantity -> offers.get(productAndQuantity.getKey()).
                        getDiscount(productAndQuantity.getKey(), productAndQuantity.getValue(), catalog.getUnitPrice(productAndQuantity.getKey())))
                .forEach(receipt::addDiscount);

    }

}
