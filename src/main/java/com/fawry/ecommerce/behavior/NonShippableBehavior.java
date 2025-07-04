package com.fawry.ecommerce.behavior;

public class NonShippableBehavior implements ShippingBehavior {
    @Override
    public boolean isShippable() {
        return false;
    }

    @Override
    public double getWeight() {
        return 0;
    }
}