package dojo.supermarket.model;

public class AppliedOffer {

    private SpecialOfferType offerType;
    private double argument; //TODO remove me please. I'm here to make it working but after whole refactoring I should be removed.


    public AppliedOffer(SpecialOfferType offerType, double argument) {
        this.offerType = offerType;
        this.argument = argument;
    }

    public SpecialOfferType getOfferType() {
        return offerType;
    }

    public double getArgument() {
        return argument;
    }
}
