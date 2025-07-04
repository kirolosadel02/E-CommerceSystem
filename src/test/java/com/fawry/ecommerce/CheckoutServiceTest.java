package com.fawry.ecommerce;

import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.builder.ProductBuilder;
import com.fawry.ecommerce.service.*;
import com.fawry.ecommerce.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class CheckoutServiceTest {
    private ReceiptPrinterService receiptPrinter;
    private ShippingService shippingService;
    private CheckoutService checkoutService;
    private Customer customer;
    private Cart cart;

    @BeforeEach
    void setup() {
        receiptPrinter = new ReceiptPrinterService();
        shippingService = new ShippingService(receiptPrinter);
        checkoutService = new CheckoutService(shippingService, receiptPrinter);
        customer = new Customer("John", 1000);
        cart = new Cart();
    }

    @Test
    void successfulCheckout() {
        Product cheese = new ProductBuilder().setName("Cheese").setPrice(100).setQuantity(2)
                .setExpirable(LocalDate.now().plusDays(2)).setShippable(0.2).build();
        cart.add(cheese, 2);
        assertDoesNotThrow(() -> checkoutService.checkout(customer, cart));
    }

    @Test
    void emptyCartThrows() {
        Exception ex = assertThrows(EmptyCartException.class, () -> checkoutService.checkout(customer, cart));
        assertEquals("Cart is empty", ex.getMessage());
    }

    @Test
    void expiredProductThrows() {
        Product expired = new ProductBuilder().setName("Old Cheese").setPrice(50).setQuantity(1)
                .setExpirable(LocalDate.now().minusDays(1)).setShippable(0.2).build();
        cart.add(expired, 1);
        Exception ex = assertThrows(ProductExpiredException.class, () -> checkoutService.checkout(customer, cart));
        assertTrue(ex.getMessage().contains("is expired"));
    }

    @Test
    void outOfStockThrows() {
        Product cheese = new ProductBuilder().setName("Cheese").setPrice(100).setQuantity(1)
                .setExpirable(LocalDate.now().plusDays(2)).setShippable(0.2).build();
        cart.add(cheese, 1);
        cheese.reduceQuantity(1);
        Exception ex = assertThrows(OutOfStockException.class, () -> checkoutService.checkout(customer, cart));
        assertTrue(ex.getMessage().contains("is out of stock"));
    }

    @Test
    void insufficientBalanceThrows() {
        Product cheese = new ProductBuilder().setName("Cheese").setPrice(1000).setQuantity(2)
                .setExpirable(LocalDate.now().plusDays(2)).setShippable(0.2).build();
        cart.add(cheese, 2);
        customer = new Customer("John", 100);
        Exception ex = assertThrows(InsufficientBalanceException.class, () -> checkoutService.checkout(customer, cart));
        assertEquals("Insufficient balance", ex.getMessage());
    }
}
