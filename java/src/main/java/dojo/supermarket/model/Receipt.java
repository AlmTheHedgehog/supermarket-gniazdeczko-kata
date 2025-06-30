package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Receipt {

    private final List<ReceiptItem> items = new ArrayList<>();
    private final List<Discount> discounts = new ArrayList<>();

    //should be integer
    public double getTotalPrice() {
        double total = 0.0;
        for (ReceiptItem item : items) {
            total += item.getTotalPrice();
        }
        for (Discount discount : discounts) {
            total += discount.getDiscountAmount();
        }
        return total;
    }

    public void addProduct(Product p, double quantity, double price, double totalPrice) {
        items.add(new ReceiptItem(p, quantity, price, totalPrice));
    }

    public List<ReceiptItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void addProducts(Map<Product, Double> products, SupermarketCatalog catalog) {
        products.forEach((product, quantity) -> {
            double unitPrice = catalog.getUnitPrice(product);
            double price = quantity * unitPrice;
            this.addProduct(product, quantity, unitPrice, price);
        });
    }
}
