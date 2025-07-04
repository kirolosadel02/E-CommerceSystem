package com.fawry.ecommerce.adapter;

import com.fawry.ecommerce.model.Product;
import com.fawry.ecommerce.model.Shippable;

public class ProductShippableAdapter implements Shippable {
    private Product product;
    private int quantity;

    public ProductShippableAdapter(Product product, int quantity) {
        if (!product.isShippable()) {
            throw new IllegalArgumentException("Product is not shippable");
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