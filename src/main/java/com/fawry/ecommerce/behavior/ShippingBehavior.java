package com.fawry.ecommerce.behavior;

public interface ShippingBehavior {
    boolean isShippable();
    double getWeight();
}