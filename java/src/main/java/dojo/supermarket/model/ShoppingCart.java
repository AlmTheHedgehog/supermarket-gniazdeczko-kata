package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    private final Map<Product, Quantity> productQuantities = new HashMap<>();

    List<ProductQuantity> getItems() {
        return Collections.unmodifiableList(items);
    }

    Map<Product, Quantity> productQuantities() {
        return Collections.unmodifiableMap(productQuantities);
    }

    public void addItemQuantity(Product product, double quantity) {
        Quantity newQuantity =  new Quantity(quantity);
        items.stream().filter(productQ -> productQ.productEquals(product)).count();
        items.add(new ProductQuantity(product, newQuantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product).increaseQuantity(newQuantity));
        } else {
            productQuantities.put(product, newQuantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p : productQuantities().keySet()) {
            Quantity quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);

               offer.offerType.toAppliedOffer(quantity).ifPresent(appliedOffer -> {
                   double unitPrice = catalog.getUnitPrice(p);
                   var calculator = switch (appliedOffer.getOfferType()) {
                       case TWO_FOR_AMOUNT -> new TwoForAmountCalculator();
                       case THREE_FOR_TWO -> new ThreeForTwoCalculator();
                       case TEN_PERCENT_DISCOUNT -> new TenPercentCalculator();
                       case FIVE_FOR_AMOUNT -> new FiveForAmountCalculator();
                   };

                   Discount discount = calculator.calculate(p, offer, quantity, unitPrice);

                   if (discount != null) {  // TODO: discount is probably always not null
                       receipt.addDiscount(discount);
                   }

               });

            }
        }
    }
}
