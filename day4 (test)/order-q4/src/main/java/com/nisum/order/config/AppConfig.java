package com.nisum.order.config;

import com.nisum.order.external.PaymentGatewayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PaymentGatewayClient paymentGatewayClient() {
        return new PaymentGatewayClient();
    }
}
