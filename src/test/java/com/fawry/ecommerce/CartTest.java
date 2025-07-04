package com.fawry.ecommerce;

import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.builder.ProductBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class CartTest {
    
    @Test
    void shouldAddProduct_whenQuantityIsWithinStock() {
        Product cheese = new ProductBuilder()
                .setName("Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().plusDays(2))
                .setShippable(0.2)
                .build();
        
        Cart cart = new Cart();
        assertDoesNotThrow(() -> cart.addProduct(cheese, 2));
        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
    }

    @Test
    void shouldAddMultipleProducts_whenAllQuantitiesAreValid() {
        Product cheese = new ProductBuilder()
                .setName("Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().plusDays(2))
                .setShippable(0.2)
                .build();
        
        Product biscuits = new ProductBuilder()
                .setName("Biscuits")
                .setPrice(150)
                .setQuantity(3)
                .setExpirable(LocalDate.now().plusDays(1))
                .setShippable(0.7)
                .build();
        
        Cart cart = new Cart();
        cart.addProduct(cheese, 2);
        cart.addProduct(biscuits, 1);
        
        assertEquals(2, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
        assertEquals(1, cart.getItems().get(1).getQuantity());
    }

    @Test
    void shouldAllowDuplicateProducts_whenUsingListImplementation() {
        Product cheese = new ProductBuilder()
                .setName("Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().plusDays(2))
                .setShippable(0.2)
                .build();
        
        Cart cart = new Cart();
        cart.addProduct(cheese, 1);
        cart.addProduct(cheese, 2);
        
        assertEquals(2, cart.getItems().size()); // Current implementation allows duplicates
        assertEquals(1, cart.getItems().get(0).getQuantity());
        assertEquals(2, cart.getItems().get(1).getQuantity());
    }
}