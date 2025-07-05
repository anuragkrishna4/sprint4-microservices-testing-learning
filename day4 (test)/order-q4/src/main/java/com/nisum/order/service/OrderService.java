package com.nisum.order.service;

import com.nisum.order.external.PaymentGatewayClient;
import com.nisum.order.model.Order;
import com.nisum.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentGatewayClient paymentGatewayClient;

    public Order createOrder(Order order) {
        paymentGatewayClient.charge(99.99);  // simulate external call
        return orderRepository.save(order);
    }
}
