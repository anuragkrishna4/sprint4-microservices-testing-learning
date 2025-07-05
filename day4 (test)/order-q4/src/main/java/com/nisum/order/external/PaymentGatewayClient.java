package com.nisum.order.external;

import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayClient {
    public void charge(double amount) {
        // simulate calling an external gateway
        System.out.println("Charging: $" + amount);
    }
}
