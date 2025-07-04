package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.OrderItem;
import com.fawry.ecommerce.model.Shippable;
import com.fawry.ecommerce.adapter.ProductShippableAdapter;

import java.util.List;

public class ReceiptPrinterService {

    public void printShipmentNotice(List<Shippable> shippableItems, double totalWeight) {
        System.out.println("** Shipment notice **");
        for (Shippable shippable : shippableItems) {
            int quantity = 1;
            if (shippable instanceof ProductShippableAdapter) {
                quantity = ((ProductShippableAdapter) shippable).getQuantity();
            }
            double itemWeight = shippable.getWeight();
            double totalItemWeight = itemWeight * quantity;
            String weightStr;
            if (totalItemWeight >= 1.0) {
                if (totalItemWeight == Math.floor(totalItemWeight)) {
                    weightStr = String.format("%.0fkg", totalItemWeight);
                } else {
                    weightStr = String.format("%.1fkg", totalItemWeight);
                }
            } else {
                int grams = (int) Math.round(totalItemWeight * 1000);
                weightStr = grams + "g";
            }
            System.out.println(quantity + "x " + shippable.getName() + " " + weightStr);
        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
        System.out.println();
    }

    public void printCheckoutReceipt(List<OrderItem> items, double subtotal,
                                     double shippingFee, double totalAmount, double remainingBalance) {
        System.out.println("** Checkout receipt **");
        for (OrderItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                    " " + (int)(item.getProduct().getPrice() * item.getQuantity()));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + (int)shippingFee);
        System.out.println("Amount " + (int)totalAmount);
        System.out.println("Remaining balance " + (int)remainingBalance);
    }
}
