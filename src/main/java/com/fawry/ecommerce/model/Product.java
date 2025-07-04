package com.fawry.ecommerce.model;

public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;
    protected boolean isShippable;
    protected double weight; // in kg

    public Product(String name, double price, int quantity, boolean isShippable, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isShippable = isShippable;
        this.weight = weight;
    }

    public abstract boolean isExpired();

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isShippable() {
        return isShippable;
    }

    public double getWeight() {
        return weight;
    }

    public void reduceQuantity(int qty) {
        if (qty > quantity) {
            throw new IllegalArgumentException("Not enough quantity in stock.");
        }
        this.quantity -= qty;
    }
}
