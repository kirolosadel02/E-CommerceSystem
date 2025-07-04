package com.fawry.ecommerce.behavior;

public class NonExpirableBehavior implements ExpiryBehavior {
    @Override
    public boolean isExpired() {
        return false;
    }
}