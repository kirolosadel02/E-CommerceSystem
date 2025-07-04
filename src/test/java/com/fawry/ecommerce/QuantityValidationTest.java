package com.fawry.ecommerce;

import com.fawry.ecommerce.builder.ProductBuilder;
import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.adapter.ProductShippableAdapter;
import com.fawry.ecommerce.exception.InvalidQuantityException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityValidationTest {

    @Test
    void shouldThrowException_whenAddingNegativeQuantityToCart() {
        Product product = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable()
                .build();
        
        Cart cart = new Cart();
        InvalidQuantityException ex = assertThrows(InvalidQuantityException.class, () -> cart.add(product, -1));
        assertTrue(ex.getMessage().contains("must be positive"));
    }

    @Test
    void shouldThrowException_whenAddingZeroQuantityToCart() {
        Product product = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable()
                .build();
        
        Cart cart = new Cart();
        InvalidQuantityException ex = assertThrows(InvalidQuantityException.class, () -> cart.add(product, 0));
        assertTrue(ex.getMessage().contains("must be positive"));
    }

    @Test
    void shouldThrowException_whenAddingQuantityExceedsStock() {
        Product product = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable()
                .build();
        
        Cart cart = new Cart();
        InvalidQuantityException ex = assertThrows(InvalidQuantityException.class, () -> cart.add(product, 10));
        assertTrue(ex.getMessage().contains("exceeds available stock"));
    }

    @Test
    void shouldThrowException_whenReducingNegativeQuantity() {
        Product product = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable()
                .build();
        
        InvalidQuantityException ex = assertThrows(InvalidQuantityException.class, () -> product.reduceQuantity(-1));
        assertTrue(ex.getMessage().contains("must be positive"));
    }

    @Test
    void shouldThrowException_whenReducingZeroQuantity() {
        Product product = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable()
                .build();
        
        InvalidQuantityException ex = assertThrows(InvalidQuantityException.class, () -> product.reduceQuantity(0));
        assertTrue(ex.getMessage().contains("must be positive"));
    }

    @Test
    void shouldThrowException_whenReducingQuantityExceedsStock() {
        Product product = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable()
                .build();
        
        InvalidQuantityException ex = assertThrows(InvalidQuantityException.class, () -> product.reduceQuantity(10));
        assertTrue(ex.getMessage().contains("Cannot reduce quantity"));
    }

    @Test
    void shouldThrowException_whenCreatingAdapterWithNegativeQuantity() {
        Product product = new ProductBuilder()
                .setName("Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setShippable(0.2)
                .build();
        
        InvalidQuantityException ex = assertThrows(InvalidQuantityException.class, () -> new ProductShippableAdapter(product, -1));
        assertTrue(ex.getMessage().contains("must be positive"));
    }

    @Test
    void shouldThrowException_whenCreatingAdapterWithZeroQuantity() {
        Product product = new ProductBuilder()
                .setName("Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setShippable(0.2)
                .build();
        
        InvalidQuantityException ex = assertThrows(InvalidQuantityException.class, () -> new ProductShippableAdapter(product, 0));
        assertTrue(ex.getMessage().contains("must be positive"));
    }
} 