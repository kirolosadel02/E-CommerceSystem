package com.fawry.ecommerce.model;

import com.fawry.ecommerce.behavior.ExpiryBehavior;
import com.fawry.ecommerce.behavior.ShippingBehavior;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private ExpiryBehavior expiryBehavior;
    private ShippingBehavior shippingBehavior;

    public Product(String name, double price, int quantity,
                   ExpiryBehavior expiryBehavior, ShippingBehavior shippingBehavior) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryBehavior = expiryBehavior;
        this.shippingBehavior = shippingBehavior;
    }

    // Delegate behavior to strategy objects
    public boolean isExpired() {
        return expiryBehavior.isExpired();
    }

    public boolean isShippable() {
        return shippingBehavior.isShippable();
    }

    public double getShippingWeight() {
        return shippingBehavior.getWeight();
    }

    // Standard getters
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
        if (qty > quantity) {
            throw new IllegalArgumentException("Not enough quantity in stock.");
        }
        this.quantity -= qty;
    }
}
