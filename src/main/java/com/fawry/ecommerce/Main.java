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
        Product tv = new ProductBuilder()
                .setName("TV")
                .setPrice(200)
                .setQuantity(3)
                .setNonExpirable()
                .setShippable(5.0)
                .build();
        Product mobile = new ProductBuilder()
                .setName("Mobile")
                .setPrice(300)
                .setQuantity(2)
                .setNonExpirable()
                .setShippable(0.4)
                .build();
        Product scratchCard = new ProductBuilder()
                .setName("Scratch Card")
                .setPrice(50)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable()
                .build();
        // Product expiredCheese = new ProductBuilder()
        //         .setName("Expired Cheese")
        //         .setPrice(80)
        //         .setQuantity(1)
        //         .setExpirable(java.time.LocalDate.now().minusDays(1))
        //         .setShippable(0.2)
        //         .build();
        // Product outOfStockTV = new ProductBuilder()
        //         .setName("Out of Stock TV")
        //         .setPrice(500)
        //         .setQuantity(0)
        //         .setNonExpirable()
        //         .setShippable(5.0)
        //         .build();

        ReceiptPrinterService receiptPrinter = new ReceiptPrinterService();
        ShippingService shippingService = new ShippingService(receiptPrinter);
        CheckoutService checkoutService = new CheckoutService(shippingService, receiptPrinter);

        try {
            Cart cart = new Cart();
            cart.addProduct(cheese, 2); // valid
            cart.addProduct(biscuits, 1); // valid
            cart.addProduct(tv, 1); // valid
            cart.addProduct(mobile, 1); // valid
            cart.addProduct(scratchCard, 1); // valid
            // cart.addProduct(cheese, 3); // would throw exception: quantity exceeds stock
            // cart.addProduct(expiredCheese, 1); // would throw exception: product expired
            // cart.addProduct(outOfStockTV, 1); // would throw exception: out of stock
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }
}
