package com.fawry.ecommerce.behavior;

import java.time.LocalDate;

public class ExpirableBehavior implements ExpiryBehavior {
    private final LocalDate expiryDate;

    public ExpirableBehavior(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}