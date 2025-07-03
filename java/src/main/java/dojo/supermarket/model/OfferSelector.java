package dojo.supermarket.model;

import java.util.Map;
import java.util.Optional;

public class OfferSelector {
    private final Map<Product, Offer> offers;

    public OfferSelector(Map<Product, Offer> offers) {
        this.offers = offers;
    }

    public Optional<AppliedOffer> selectOffer(Product product, Quantity quantity) {
        Offer offer = offers.get(product);
        if (offer == null) {
            return Optional.empty();
        }

        return offer.offerType.toAppliedOffer(quantity, offer.argument);
    }
}
