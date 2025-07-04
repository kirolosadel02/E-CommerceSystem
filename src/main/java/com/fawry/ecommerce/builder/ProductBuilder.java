package com.fawry.ecommerce.builder;

import com.fawry.ecommerce.model.Product;
import com.fawry.ecommerce.behavior.*;
import com.fawry.ecommerce.exception.ProductBuilderException;
import java.time.LocalDate;

public class ProductBuilder {
    private String name;
    private double price;
    private int quantity;
    private ExpiryBehavior expiryBehavior;
    private ShippingBehavior shippingBehavior;

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductBuilder setExpirable(LocalDate expiryDate) {
        this.expiryBehavior = new ExpirableBehavior(expiryDate);
        return this;
    }

    public ProductBuilder setNonExpirable() {
        this.expiryBehavior = new NonExpirableBehavior();
        return this;
    }

    public ProductBuilder setShippable(double weight) {
        this.shippingBehavior = new ShippableBehavior(weight);
        return this;
    }

    public ProductBuilder setNonShippable() {
        this.shippingBehavior = new NonShippableBehavior();
        return this;
    }

    public Product build() {
        if (name == null || name.trim().isEmpty()) {
            throw new ProductBuilderException("Product name cannot be null or empty");
        }
        if (expiryBehavior == null) {
            throw new ProductBuilderException("Product '" + name + "' must have expiry behavior set (expirable or non-expirable)");
        }
        if (shippingBehavior == null) {
            throw new ProductBuilderException("Product '" + name + "' must have shipping behavior set (shippable or non-shippable)");
        }
        if (price <= 0) {
            throw new ProductBuilderException("Product '" + name + "' must have a positive price, got: " + price);
        }
        if (quantity < 0) {
            throw new ProductBuilderException("Product '" + name + "' quantity cannot be negative, got: " + quantity);
        }
        return new Product(name, price, quantity, expiryBehavior, shippingBehavior);
    }
}