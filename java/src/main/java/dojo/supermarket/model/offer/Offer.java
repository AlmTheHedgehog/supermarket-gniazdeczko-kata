package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

import java.util.Map;

public interface Offer {
    Discount getDiscount(Product product, Double amountOfProduct, double productPrice);
}
