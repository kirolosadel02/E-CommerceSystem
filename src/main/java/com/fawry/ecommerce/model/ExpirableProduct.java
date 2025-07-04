package com.fawry.ecommerce.model;

import java.time.LocalDate;

public class ExpirableProduct extends Product implements Shippable {
    private LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate, boolean isShippable, double weight) {
        super(name, price, quantity, isShippable, weight);
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
