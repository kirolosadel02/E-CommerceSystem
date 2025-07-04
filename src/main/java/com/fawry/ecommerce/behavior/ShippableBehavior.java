package com.fawry.ecommerce.behavior;

public class ShippableBehavior implements ShippingBehavior {
    private final double weight;

    public ShippableBehavior(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}