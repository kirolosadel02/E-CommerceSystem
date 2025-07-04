package com.fawry.ecommerce;

import com.fawry.ecommerce.builder.ProductBuilder;
import com.fawry.ecommerce.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ProductExpiryTest {

    @Test
    void shouldBeExpired_whenExpiryDateIsInPast() {
        Product expiredProduct = new ProductBuilder()
                .setName("Expired Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().minusDays(1))
                .setShippable(0.2)
                .build();
        
        assertTrue(expiredProduct.isExpired());
    }

    @Test
    void shouldBeExpired_whenExpiryDateIsToday() {
        Product expiringToday = new ProductBuilder()
                .setName("Expiring Today Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now())
                .setShippable(0.2)
                .build();
        
        assertTrue(expiringToday.isExpired(), "Product should be expired on its expiry date");
    }

    @Test
    void shouldNotBeExpired_whenExpiryDateIsInFuture() {
        Product freshProduct = new ProductBuilder()
                .setName("Fresh Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().plusDays(5))
                .setShippable(0.2)
                .build();
        
        assertFalse(freshProduct.isExpired());
    }

    @Test
    void shouldNotBeExpired_whenExpiryDateIsTomorrow() {
        Product expiringTomorrow = new ProductBuilder()
                .setName("Expiring Tomorrow Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().plusDays(1))
                .setShippable(0.2)
                .build();
        
        assertFalse(expiringTomorrow.isExpired(), "Product should not be expired before its expiry date");
    }

    @Test
    void shouldBeExpired_whenExpiryDateIsYesterday() {
        Product expiredYesterday = new ProductBuilder()
                .setName("Expired Yesterday Cheese")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().minusDays(1))
                .setShippable(0.2)
                .build();
        
        assertTrue(expiredYesterday.isExpired(), "Product should be expired after its expiry date");
    }

    @Test
    void shouldNeverBeExpired_whenProductIsNonExpirable() {
        Product nonExpirableProduct = new ProductBuilder()
                .setName("TV")
                .setPrice(500)
                .setQuantity(2)
                .setNonExpirable()
                .setShippable(5.0)
                .build();
        
        assertFalse(nonExpirableProduct.isExpired());
    }

    @Test
    void shouldNeverBeExpired_whenProductIsNonExpirableAndNonShippable() {
        Product scratchCard = new ProductBuilder()
                .setName("Scratch Card")
                .setPrice(50)
                .setQuantity(10)
                .setNonExpirable()
                .setNonShippable()
                .build();
        
        assertFalse(scratchCard.isExpired());
    }

    @Test
    void shouldHandleMultipleExpiryChecks_consistently() {
        Product product = new ProductBuilder()
                .setName("Test Product")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().plusDays(3))
                .setShippable(0.2)
                .build();
        
        boolean firstCheck = product.isExpired();
        boolean secondCheck = product.isExpired();
        boolean thirdCheck = product.isExpired();
        
        assertEquals(firstCheck, secondCheck);
        assertEquals(secondCheck, thirdCheck);
        assertFalse(firstCheck);
    }

    @Test
    void shouldHandleEdgeCase_whenExpiryDateIsFarInPast() {
        Product veryOldProduct = new ProductBuilder()
                .setName("Very Old Product")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().minusYears(1))
                .setShippable(0.2)
                .build();
        
        assertTrue(veryOldProduct.isExpired());
    }

    @Test
    void shouldHandleEdgeCase_whenExpiryDateIsFarInFuture() {
        Product veryFreshProduct = new ProductBuilder()
                .setName("Very Fresh Product")
                .setPrice(100)
                .setQuantity(5)
                .setExpirable(LocalDate.now().plusYears(1))
                .setShippable(0.2)
                .build();
        
        assertFalse(veryFreshProduct.isExpired());
    }
} 