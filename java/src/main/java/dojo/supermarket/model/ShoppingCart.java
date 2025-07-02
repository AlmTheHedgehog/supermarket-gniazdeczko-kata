package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    private final Map<Product, Double> productQuantities = new HashMap<>();

    List<ProductQuantity> getItems() {
        return Collections.unmodifiableList(items);
    }

    void addItem(Product product) {
        addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return Collections.unmodifiableMap(productQuantities);
    }

    public void addItemQuantity(Product product, double quantity) {
        items.stream().filter(productQ -> productQ.productEquals(product)).count();
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p : productQuantities().keySet()) {
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                double unitPrice = catalog.getUnitPrice(p);
                int quantityAsInt = (int) quantity;
                Discount discount = null;
                if (offer.offerType == SpecialOfferType.TWO_FOR_AMOUNT && quantityAsInt >= 2) {
                    var calculator = new TwoForAmountCalculator();
                    discount = calculator.calculate(p, offer, quantityAsInt, unitPrice, quantity);
                }
                if (offer.offerType == SpecialOfferType.THREE_FOR_TWO && quantityAsInt > 2) {
                    var calculator = new ThreeForTwoCalculator();
                    discount = calculator.calculate(p, quantityAsInt, unitPrice, quantity);
                }
                if (offer.offerType == SpecialOfferType.TEN_PERCENT_DISCOUNT) {
                    var calculator = new TenPercentCalculator();
                    discount = calculator.calculate(p, offer, unitPrice, quantity);
                }
                if (offer.offerType == SpecialOfferType.FIVE_FOR_AMOUNT && quantityAsInt >= 5) {
                    var calculator = new FiveForAmountCalculator();
                    discount = calculator.calculate(p, offer, quantityAsInt, unitPrice, quantity);
                }


                if (discount != null)
                    receipt.addDiscount(discount);
            }
        }
    }
}
