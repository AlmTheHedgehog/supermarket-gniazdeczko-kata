package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.product.Product;

public class ThreeForTwoOffer implements Offer {
    //TODO can do some abstract XfoNOffer and create classes as needed. Like 3for2 or 5for2

    @Override
    public Discount getDiscount(final Product product) {
        int viableForDiscount = ((int)product.getQuantity())/3;
        double discountAmount = viableForDiscount * product.getPrice();
        return new Discount(product, "3 for 2", -discountAmount);
    }
}
