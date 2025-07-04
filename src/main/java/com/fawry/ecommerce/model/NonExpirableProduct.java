package com.fawry.ecommerce.model;

public class NonExpirableProduct extends Product implements Shippable {
    public NonExpirableProduct(String name, double price, int quantity, boolean isShippable, double weight) {
        super(name, price, quantity, isShippable, weight);
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
