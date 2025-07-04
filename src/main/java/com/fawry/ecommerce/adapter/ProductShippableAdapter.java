package com.fawry.ecommerce.adapter;

import com.fawry.ecommerce.exception.InvalidQuantityException;
import com.fawry.ecommerce.model.Product;
import com.fawry.ecommerce.model.Shippable;
import com.fawry.ecommerce.exception.NonShippableProductException;

public class ProductShippableAdapter implements Shippable {
    private final Product product;
    private final int quantity;

    public ProductShippableAdapter(Product product, int quantity) {
        if (product == null) {
            throw new NonShippableProductException("Product cannot be null");
        }
        if (!product.isShippable()) {
            throw new NonShippableProductException("Product '" + product.getName() + "' is not shippable");
        }
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be positive, got: " + quantity);
        }
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getWeight() {
        return product.getShippingWeight();
    }

    public int getQuantity() {
        return quantity;
    }
}