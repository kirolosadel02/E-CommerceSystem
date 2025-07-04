package com.fawry.ecommerce.service;

import com.fawry.ecommerce.exception.*;
import com.fawry.ecommerce.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutService {

    private final ShippingService shippingService;
    private final ReceiptPrinterService receiptPrinter;

    public CheckoutService(ShippingService shippingService, ReceiptPrinterService receiptPrinter) {
        this.shippingService = shippingService;
        this.receiptPrinter = receiptPrinter;
    }

    public void checkout(Customer customer, Cart cart) {
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

        double subtotal = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        List<OrderItem> shippableItems = cart.getItems().stream()
                .filter(item -> item.getProduct().isShippable())
                .collect(Collectors.toList());

        double shippingFee = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        // Deduct product quantities
        for (OrderItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.deductBalance(totalAmount);

        // Ship items
        shippingService.ship(shippableItems);

        // Print receipt
        receiptPrinter.printCheckoutReceipt(cart.getItems(), subtotal, shippingFee, totalAmount, customer.getBalance());
    }
}
