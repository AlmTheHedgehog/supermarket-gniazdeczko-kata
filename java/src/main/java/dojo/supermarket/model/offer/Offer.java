package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.product.Product;

public interface Offer {
    Discount getDiscount(final Product product);
}
