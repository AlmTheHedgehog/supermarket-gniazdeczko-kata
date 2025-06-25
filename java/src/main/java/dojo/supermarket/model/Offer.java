package dojo.supermarket.model;

public class Offer {

    // FIXME - Data Class. true power of objects is that they can contain behavior types or operations on their data

    SpecialOfferType offerType;
    private final Product product;
    double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    // FIXME - dead code
    Product getProduct() {
        return product;
    }
}
