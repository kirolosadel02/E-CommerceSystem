package com.fawry.ecommerce;

import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.service.CheckoutService;
import com.fawry.ecommerce.service.ReceiptPrinterService;
import com.fawry.ecommerce.service.ShippingService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("John", 1000);

        ExpirableProduct cheese = new ExpirableProduct("Cheese", 100, 5, LocalDate.now().plusDays(10), true, 0.4);
        ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 150, 3, LocalDate.now().plusDays(5), true, 0.7);
        NonExpirableProduct tv = new NonExpirableProduct("TV", 300, 2, true, 10);
        NonExpirableProduct scratchCard = new NonExpirableProduct("Scratch Card", 50, 10, false, 0);

        Cart cart = new Cart();
        cart.addProduct(cheese, 2);
        cart.addProduct(biscuits, 1);
        cart.addProduct(scratchCard, 1);

        ReceiptPrinterService receiptPrinter = new ReceiptPrinterService();
        ShippingService shippingService = new ShippingService(receiptPrinter);
        CheckoutService checkoutService = new CheckoutService(shippingService, receiptPrinter);

        checkoutService.checkout(customer, cart);
    }
}
