package com.nisum.price;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class PriceFacadeTest {

    @Spy
    private PriceCalculator calculator = new PriceCalculator();

    private PriceFacade facade;

    @Test
    public void whenTaxCalculationFails_thenReturnsBasePrice() {
        doThrow(new ArithmeticException("Tax failure"))
                .when(calculator).calculateTax(anyInt());

        facade = new PriceFacade(calculator);

        int price = facade.getPrice(100);
        assertEquals(100, price);
    }


    @Test
    public void afterReset_thenRealMethodIsCalled() {
        reset(calculator);
        facade = new PriceFacade(calculator);

        int price = facade.getPrice(100);
        assertEquals(118, price);
    }
}
