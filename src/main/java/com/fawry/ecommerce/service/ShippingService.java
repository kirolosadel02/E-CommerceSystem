package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.OrderItem;
import com.fawry.ecommerce.model.Product;
import com.fawry.ecommerce.model.Shippable;

import java.util.List;

public class ShippingService {
    private static final double BASE_FEE = 30.0;

    public void shipItems(List<OrderItem> items) {
        double totalWeight = 0;

        System.out.println("** Shipment notice **");
        for (OrderItem item : items) {
            Product product = item.getProduct();
            if (product instanceof Shippable && product.isShippable()) {
                double weightPerUnit = product.getWeight();
                double totalItemWeight = weightPerUnit * item.getQuantity();
                String weightStr = formatWeight(totalItemWeight);

                System.out.println(item.getQuantity() + "x " + product.getName() + " " + weightStr);
                totalWeight += totalItemWeight;
            }
        }

        String totalWeightStr = formatWeight(totalWeight);
        System.out.println("Total package weight " + totalWeightStr);
    }

    private String formatWeight(double weightKg) {
        if (weightKg < 1.0) {
            return (int) (weightKg * 1000) + "g";
        } else {
            return String.format("%.1fkg", weightKg);
        }
    }

    public double calculateShippingFee() {
        return BASE_FEE;
    }
}
