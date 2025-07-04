package com.fawry.ecommerce.service;

import com.fawry.ecommerce.exception.*;
import com.fawry.ecommerce.model.*;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new EmptyCartException("Cart is empty.");
        }

        List<OrderItem> items = cart.getItems();
        List<OrderItem> shippableItems = new ArrayList<>();

        for (OrderItem item : items) {
            Product product = item.getProduct();

            if (product.isExpired()) {
                throw new ProductExpiredException(product.getName() + " is expired.");
            }

            if (item.getQuantity() > product.getQuantity()) {
                throw new OutOfStockException(product.getName() + " does not have enough stock.");
            }

            product.reduceQuantity(item.getQuantity());

            if (product.isShippable()) {
                shippableItems.add(item);
            }
        }

        double subtotal = cart.calculateSubtotal();
        double shippingFee = shippableItems.isEmpty() ? 0 : shippingService.calculateShippingFee();
        double totalAmount = subtotal + shippingFee;

        if (totalAmount > customer.getBalance()) {
            throw new InsufficientBalanceException("Customer has insufficient balance.");
        }

        customer.deductBalance(totalAmount);

        if (!shippableItems.isEmpty()) {
            shippingService.shipItems(shippableItems);
        }

        System.out.println("** Checkout receipt **");
        for (OrderItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProductName() + " " + (item.getProduct().getPrice() * item.getQuantity()));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFee);
        System.out.println("Amount " + totalAmount);
        System.out.println("Customer balance after payment: " + customer.getBalance());
    }
}
