package com.fawry.ecommerce.model;

import com.fawry.ecommerce.behavior.ExpiryBehavior;
import com.fawry.ecommerce.behavior.ShippingBehavior;
import com.fawry.ecommerce.exception.InvalidQuantityException;

public class Product {
    private final String name;
    private final double price;
    private int quantity;
    private final ExpiryBehavior expiryBehavior;
    private final ShippingBehavior shippingBehavior;

    public Product(String name, double price, int quantity,
                   ExpiryBehavior expiryBehavior, ShippingBehavior shippingBehavior) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryBehavior = expiryBehavior;
        this.shippingBehavior = shippingBehavior;
    }

    public boolean isExpired() {
        return expiryBehavior.isExpired();
    }

    public boolean isShippable() {
        return shippingBehavior.isShippable();
    }

    public double getShippingWeight() {
        return shippingBehavior.getWeight();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int qty) {
        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity to reduce must be positive, got: " + qty);
        }
        if (qty > quantity) {
            throw new InvalidQuantityException("Cannot reduce quantity by " + qty + " when only " + quantity + " available for product: " + name);
        }
        this.quantity -= qty;
    }
}
