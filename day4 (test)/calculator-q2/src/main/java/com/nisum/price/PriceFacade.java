package com.nisum.price;

public class PriceFacade {

    private final PriceCalculator calculator;

    public PriceFacade(PriceCalculator calculator) {
        this.calculator = calculator;
    }

    public int getPrice(int basePrice) {
        try {
            return calculator.getPrice(basePrice);
        } catch (ArithmeticException ex) {
            return basePrice;
        }
    }
}
