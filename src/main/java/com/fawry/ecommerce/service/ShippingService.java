package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.OrderItem;

import java.util.List;

public class ShippingService {

    private final ReceiptPrinterService receiptPrinter;

    public ShippingService(ReceiptPrinterService receiptPrinter) {
        this.receiptPrinter = receiptPrinter;
    }

    public void ship(List<OrderItem> shippableItems) {
        double totalWeight = shippableItems.stream()
                .mapToDouble(item -> item.getProduct().getWeight() * item.getQuantity())
                .sum();

        if (!shippableItems.isEmpty()) {
            receiptPrinter.printShipmentNotice(shippableItems, totalWeight);
        }
    }

    public double calculateShippingFee(List<OrderItem> shippableItems) {
        double totalWeight = shippableItems.stream()
                .mapToDouble(item -> item.getProduct().getWeight() * item.getQuantity())
                .sum();

        return totalWeight * 10; // Example: 10 per kg
    }
}
