package com.fawry.ecommerce;

import com.fawry.ecommerce.builder.ProductBuilder;
import com.fawry.ecommerce.exception.ProductBuilderException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductBuilderTest {

    @Test
    void shouldThrowException_whenNameIsNull() {
        ProductBuilder builder = new ProductBuilder()
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable();
        
        ProductBuilderException ex = assertThrows(ProductBuilderException.class, builder::build);
        assertTrue(ex.getMessage().contains("name cannot be null or empty"));
    }

    @Test
    void shouldThrowException_whenNameIsEmpty() {
        ProductBuilder builder = new ProductBuilder()
                .setName("")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable();
        
        ProductBuilderException ex = assertThrows(ProductBuilderException.class, builder::build);
        assertTrue(ex.getMessage().contains("name cannot be null or empty"));
    }

    @Test
    void shouldThrowException_whenPriceIsNegative() {
        ProductBuilder builder = new ProductBuilder()
                .setName("Test Product")
                .setPrice(-10)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable();
        
        ProductBuilderException ex = assertThrows(ProductBuilderException.class, builder::build);
        assertTrue(ex.getMessage().contains("positive price"));
    }

    @Test
    void shouldThrowException_whenPriceIsZero() {
        ProductBuilder builder = new ProductBuilder()
                .setName("Test Product")
                .setPrice(0)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable();
        
        ProductBuilderException ex = assertThrows(ProductBuilderException.class, builder::build);
        assertTrue(ex.getMessage().contains("positive price"));
    }

    @Test
    void shouldThrowException_whenQuantityIsNegative() {
        ProductBuilder builder = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(-5)
                .setNonExpirable()
                .setNonShippable();
        
        ProductBuilderException ex = assertThrows(ProductBuilderException.class, builder::build);
        assertTrue(ex.getMessage().contains("quantity cannot be negative"));
    }

    @Test
    void shouldThrowException_whenExpiryBehaviorIsMissing() {
        ProductBuilder builder = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setNonShippable();
        
        ProductBuilderException ex = assertThrows(ProductBuilderException.class, builder::build);
        assertTrue(ex.getMessage().contains("expiry behavior"));
    }

    @Test
    void shouldThrowException_whenShippingBehaviorIsMissing() {
        ProductBuilder builder = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable();
        
        ProductBuilderException ex = assertThrows(ProductBuilderException.class, builder::build);
        assertTrue(ex.getMessage().contains("shipping behavior"));
    }

    @Test
    void shouldBuildProduct_whenAllFieldsAreValid() {
        ProductBuilder builder = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setNonExpirable()
                .setNonShippable();
        
        assertDoesNotThrow(builder::build);
    }
} 