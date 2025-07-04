package com.fawry.ecommerce;

import com.fawry.ecommerce.builder.ProductBuilder;
import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.adapter.ProductShippableAdapter;
import com.fawry.ecommerce.exception.NonShippableProductException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShippingValidationTest {

    @Test
    void shouldThrowException_whenCreatingAdapterWithNullProduct() {
        NonShippableProductException ex = assertThrows(NonShippableProductException.class, () -> new ProductShippableAdapter(null, 1));
        assertTrue(ex.getMessage().contains("cannot be null"));
    }

    @Test
    void shouldThrowException_whenCreatingAdapterWithNonShippableProduct() {
        Product product = new ProductBuilder()
                .setName("Scratch Card")
                .setPrice(50)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable()
                .build();
        
        NonShippableProductException ex = assertThrows(NonShippableProductException.class, () -> new ProductShippableAdapter(product, 1));
        assertTrue(ex.getMessage().contains("is not shippable"));
    }

    @Test
    void shouldCreateAdapter_whenProductIsShippable() {
        Product product = new ProductBuilder()
                .setName("Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setShippable(0.2)
                .build();
        
        assertDoesNotThrow(() -> new ProductShippableAdapter(product, 1));
    }

    @Test
    void shouldReturnCorrectWeight_whenCreatingAdapter() {
        Product product = new ProductBuilder()
                .setName("Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setShippable(0.2)
                .build();
        
        ProductShippableAdapter adapter = new ProductShippableAdapter(product, 2);
        assertEquals(0.2, adapter.getWeight());
        assertEquals("Cheese", adapter.getName());
        assertEquals(2, adapter.getQuantity());
    }
} 