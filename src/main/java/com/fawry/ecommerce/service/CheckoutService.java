package com.fawry.ecommerce.service;

import com.fawry.ecommerce.exception.*;
import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.adapter.ProductShippableAdapter;

import java.util.List;

public class CheckoutService {

    private final ShippingService shippingService;
    private final ReceiptPrinterService receiptPrinter;

    public CheckoutService(ShippingService shippingService, ReceiptPrinterService receiptPrinter) {
        this.shippingService = shippingService;
        this.receiptPrinter = receiptPrinter;
    }

    public void checkout(Customer customer, Cart cart) {
        validateCart(cart);

        // Collect shippable items
        List<Shippable> shippableItems = cart.getItems().stream()
            .filter(item -> item.getProduct().isShippable())
            .map(item -> new ProductShippableAdapter(item.getProduct(), item.getQuantity()))
            .collect(java.util.stream.Collectors.toList());

        double subtotal = calculateSubtotal(cart.getItems());
        double shippingFee = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        processOrder(cart.getItems(), customer, totalAmount);

        // Ship items
        shippingService.ship(shippableItems);

        // Print receipt
        receiptPrinter.printCheckoutReceipt(cart.getItems(), subtotal, shippingFee,
                totalAmount, customer.getBalance());
    }

    private void validateCart(Cart cart) {
        if (cart.getItems().isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }

        for (OrderItem item : cart.getItems()) {
            if (item.getProduct().isExpired()) {
                throw new ProductExpiredException("Product " + item.getProductName() + " is expired");
            }
            if (item.getProduct().getQuantity() < item.getQuantity()) {
                throw new OutOfStockException("Product " + item.getProductName() + " is out of stock");
            }
        }
    }

    private double calculateSubtotal(List<OrderItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    private void processOrder(List<OrderItem> items, Customer customer, double totalAmount) {
        // Deduct product quantities
        for (OrderItem item : items) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.deductBalance(totalAmount);
    }
}