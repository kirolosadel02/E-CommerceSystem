package com.fawry.ecommerce.model;

import java.util.List;

public class Order {
    private List<OrderItem> items;
    private double subtotal;
    private double shippingFee;
    private double totalAmount;

    public Order(List<OrderItem> items, double shippingFee) {
        this.items = items;
        this.subtotal = items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
        this.shippingFee = shippingFee;
        this.totalAmount = subtotal + shippingFee;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
