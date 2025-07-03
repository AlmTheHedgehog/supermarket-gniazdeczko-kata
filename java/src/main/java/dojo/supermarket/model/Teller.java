package dojo.supermarket.model;

import java.util.HashMap;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private final Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    //This method is exposed to external systems so no change into signature allowed
    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        offers.put(product, new Offer(offerType, argument));
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        Map<Product, Quantity> productQuantities = theCart.productQuantities();
        productQuantities.forEach((product, quantity) -> {
            double unitPrice = catalog.getUnitPrice(product);
            double price = quantity.asDouble() * unitPrice;
            receipt.addProduct(product, quantity.asDouble(), unitPrice, price);
            OfferSelector offerSelector = new OfferSelector(offers);
            offerSelector.selectOffer(product, quantity)
                    .map(CalculatorFactory::instanceOf)
                    .map(calculator -> calculator.calculate(product, quantity, unitPrice))
                    .ifPresent(receipt::addDiscount);
        });

        return receipt;
    }
}
