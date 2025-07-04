package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.OrderItem;

import java.util.List;

public class ReceiptPrinterService {

    public void printShipmentNotice(List<OrderItem> shippableItems, double totalWeight) {
        System.out.println("** Shipment notice **");
        for (OrderItem item : shippableItems) {
            System.out.println(item.getQuantity() + "x " + item.getProductName() + " " + formatWeight(item.getProduct().getWeight()));
        }
        System.out.println("Total package weight " + formatWeight(totalWeight));
    }

    public void printCheckoutReceipt(List<OrderItem> items, double subtotal, double shippingFee, double totalAmount, double balance) {
        System.out.println("** Checkout receipt **");
        for (OrderItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProductName() + " " + (item.getProduct().getPrice() * item.getQuantity()));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFee);
        System.out.println("Amount " + totalAmount);
        System.out.println("Customer balance after payment: " + balance);
    }

    private String formatWeight(double weightKg) {
        if (weightKg < 1.0) {
            return (int) (weightKg * 1000) + "g";
        } else {
            return String.format("%.1fkg", weightKg);
        }
    }
}
