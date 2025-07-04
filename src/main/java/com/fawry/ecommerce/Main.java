package com.fawry.ecommerce;

import com.fawry.ecommerce.builder.ProductBuilder;
import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.service.CheckoutService;
import com.fawry.ecommerce.service.ReceiptPrinterService;
import com.fawry.ecommerce.service.ShippingService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("John", 1000);

        Product cheese = new ProductBuilder()
                .setName("Cheese")
                .setPrice(100)
                .setQuantity(2)
                .setExpirable(LocalDate.now().plusDays(4))
                .setShippable(0.2)
                .build();
        Product biscuits = new ProductBuilder()
                .setName("Biscuits")
                .setPrice(150)
                .setQuantity(1)
                .setExpirable(LocalDate.now().plusDays(2))
                .setShippable(0.7)
                .build();

        ReceiptPrinterService receiptPrinter = new ReceiptPrinterService();
        ShippingService shippingService = new ShippingService(receiptPrinter);
        CheckoutService checkoutService = new CheckoutService(shippingService, receiptPrinter);

        try {
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }
}


