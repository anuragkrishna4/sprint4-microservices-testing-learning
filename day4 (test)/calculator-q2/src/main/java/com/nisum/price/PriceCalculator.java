package com.nisum.price;

public class PriceCalculator {


    public int getPrice(int basePrice) {
        return basePrice + calculateTax(basePrice);
    }

    int calculateTax(int basePrice) {
        return (int) (basePrice * 0.18);
    }
}
