package com.ecommerce.order.manager.broker.impl;

import com.ecommerce.order.manager.broker.OrderEventPublisher;
import com.ecommerce.order.manager.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventPublisherImpl implements OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.cloud.stream.bindings.pedidoCriado-out-0.destination}")
    private String orderCreatedQueue;

    @Value("${spring.cloud.stream.bindings.pedidoConfirmado-out-0.destination}")
    private String orderConfirmedQueue;

    @Override
    public void orderCreate(Order order) {
        rabbitTemplate.convertAndSend(orderCreatedQueue, order);
    }

    @Override
    public void orderConfimed(Order order) {
        rabbitTemplate.convertAndSend(orderConfirmedQueue, order);
    }
}
