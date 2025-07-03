package dojo.supermarket.model;

public class AppliedOffer {

    private SpecialOfferType offerType;


    public AppliedOffer(SpecialOfferType offerType) {
        this.offerType = offerType;
    }

    public SpecialOfferType getOfferType() {
        return offerType;
    }
}
