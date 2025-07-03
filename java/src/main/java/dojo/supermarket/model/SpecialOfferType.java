package dojo.supermarket.model;

import java.util.Optional;

public enum SpecialOfferType {
    THREE_FOR_TWO (3),
    TEN_PERCENT_DISCOUNT (1),
    TWO_FOR_AMOUNT (2),
    FIVE_FOR_AMOUNT (5),
    ;

    private final int groupingSize;

    SpecialOfferType(int groupingSize) {
        this.groupingSize = groupingSize;
    }


    public Optional<AppliedOffer> toAppliedOffer(Quantity quantity) {
        if(groupingSize >= quantity.asDouble()) {
            return Optional.of(new AppliedOffer(this));
        }  else {
            return Optional.empty();
        }
    }
}


