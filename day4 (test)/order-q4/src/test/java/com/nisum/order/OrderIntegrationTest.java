package com.nisum.order;

import com.nisum.order.external.PaymentGatewayClient;
import com.nisum.order.model.Order;
import com.nisum.order.repository.OrderRepository;
import com.nisum.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PaymentGatewayClient paymentGatewayClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testCreateOrder_andVerifyContextIsolation() {
        Order request = Order.builder().item("Phone").build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Order> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Order> response = restTemplate.postForEntity("/orders", entity, Order.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        verify(paymentGatewayClient, times(1)).charge(99.99);
        assertThat(orderService).isNotNull();
        assertThat(orderRepository).isNotNull();
    }
}
