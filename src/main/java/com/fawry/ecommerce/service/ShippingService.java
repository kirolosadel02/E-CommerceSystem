package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.Shippable;
import com.fawry.ecommerce.adapter.ProductShippableAdapter;

import java.util.List;

public class ShippingService {

    private final ReceiptPrinterService receiptPrinter;

    public ShippingService(ReceiptPrinterService receiptPrinter) {
        this.receiptPrinter = receiptPrinter;
    }

    public void ship(List<Shippable> shippableItems) {
        if (!shippableItems.isEmpty()) {
            double totalWeight = 0;
            for (Shippable shippable : shippableItems) {
                int quantity = 1;
                if (shippable instanceof ProductShippableAdapter) {
                    quantity = ((ProductShippableAdapter) shippable).getQuantity();
                }
                totalWeight += shippable.getWeight() * quantity;
            }
            receiptPrinter.printShipmentNotice(shippableItems, totalWeight);
        }
    }

    public double calculateShippingFee(List<Shippable> shippableItems) {
        double totalWeight = 0;
        for (Shippable shippable : shippableItems) {
            int quantity = 1;
            if (shippable instanceof ProductShippableAdapter) {
                quantity = ((ProductShippableAdapter) shippable).getQuantity();
            }
            totalWeight += shippable.getWeight() * quantity;
        }
        return totalWeight * 10;
    }
}