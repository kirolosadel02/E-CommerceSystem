package com.fawry.ecommerce;

import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.service.CheckoutService;
import com.fawry.ecommerce.service.ShippingService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create a customer with initial balance
        Customer customer = new Customer("John", 1000);

        // Create products
        ExpirableProduct cheese = new ExpirableProduct("Cheese", 100, 5, LocalDate.now().plusDays(10), true, 0.2);
        ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 150, 3, LocalDate.now().plusDays(5), true, 0.7);
        NonExpirableProduct tv = new NonExpirableProduct("TV", 300, 2, true, 10);
        NonExpirableProduct scratchCard = new NonExpirableProduct("Scratch Card", 50, 10, false, 0);

        // Create a cart and add products
        Cart cart = new Cart();
        cart.addProduct(cheese, 2);
        cart.addProduct(biscuits, 1);
        cart.addProduct(scratchCard, 1);

        // Create services
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);

        // Perform checkout
        try {
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error during checkout: " + e.getMessage());
        }
    }
}
