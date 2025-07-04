package com.fawry.ecommerce.model;

import com.fawry.ecommerce.exception.InvalidQuantityException;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<OrderItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be positive, got: " + quantity);
        }
        if (quantity > product.getQuantity()) {
            throw new InvalidQuantityException("Requested quantity exceeds available stock");
        }
        items.add(new OrderItem(product, quantity));
    }

    public List<OrderItem> getItems() {
        return items;
    }
}