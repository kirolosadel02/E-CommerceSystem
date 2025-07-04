package com.fawry.ecommerce;

import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.builder.ProductBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class CartTest {
    @Test
    void addProductWithinStock() {
        Product cheese = new ProductBuilder()
                .setName("Cheese").setPrice(100).setQuantity(5)
                .setExpirable(LocalDate.now().plusDays(2)).setShippable(0.2).build();
        Cart cart = new Cart();
        assertDoesNotThrow(() -> cart.addProduct(cheese, 2));
        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
    }

    @Test
    void addProductExceedingStockThrows() {
        Product cheese = new ProductBuilder()
                .setName("Cheese").setPrice(100).setQuantity(2)
                .setExpirable(LocalDate.now().plusDays(2)).setShippable(0.2).build();
        Cart cart = new Cart();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cart.addProduct(cheese, 3));
        assertEquals("Requested quantity exceeds available stock", ex.getMessage());
    }

    @Test
    void addSameProductMultipleTimes() {
        Product cheese = new ProductBuilder()
                .setName("Cheese").setPrice(100).setQuantity(5)
                .setExpirable(LocalDate.now().plusDays(2)).setShippable(0.2).build();
        Cart cart = new Cart();
        cart.addProduct(cheese, 1);
        cart.addProduct(cheese, 2);
        assertEquals(2, cart.getItems().size()); // Current logic allows duplicates
    }
}
