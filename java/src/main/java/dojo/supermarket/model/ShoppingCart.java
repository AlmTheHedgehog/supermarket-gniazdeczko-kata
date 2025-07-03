package dojo.supermarket.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<Product, Quantity> productQuantities = new HashMap<>();

    Map<Product, Quantity> productQuantities() {
        return Collections.unmodifiableMap(productQuantities);
    }

    //This method is exposed to external systems so no change into signature allowed
    public void addItemQuantity(Product product, double quantity) {
        //TODO Race conditioning here. Please convert to atomic operation productQuantities.compute(..) or maybe merge?
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product).increaseQuantity(new Quantity(quantity)));
        } else {
            productQuantities.put(product, new Quantity(quantity));
        }
    }
}
