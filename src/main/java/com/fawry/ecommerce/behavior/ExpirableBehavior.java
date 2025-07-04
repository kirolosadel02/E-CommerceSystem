package com.fawry.ecommerce.behavior;

import java.time.LocalDate;

public class ExpirableBehavior implements ExpiryBehavior {
    private final LocalDate expiryDate;

    public ExpirableBehavior(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        LocalDate today = LocalDate.now();
        return today.isAfter(expiryDate) || today.isEqual(expiryDate);
    }
}