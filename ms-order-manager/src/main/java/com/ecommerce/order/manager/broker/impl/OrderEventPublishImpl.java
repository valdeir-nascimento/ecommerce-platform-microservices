package com.ecommerce.order.manager.broker.impl;

import com.ecommerce.order.manager.broker.OrderEventPublish;
import com.ecommerce.order.manager.broker.message.MessageSender;
import com.ecommerce.order.manager.mapper.OrderMapper;
import com.ecommerce.order.manager.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventPublishImpl implements OrderEventPublish {

    private final MessageSender messageSender;
    private final OrderMapper orderMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void orderCreated(Order order) {
        sendMessage("pedido-criado-out-0", order);
    }

    @Override
    public void orderConfirmed(Order order) {
        sendMessage("pedido-confirmado-out-0", order);
    }

    private void sendMessage(String bindingName, Order order) {
        try {
            final var orderDto = orderMapper.toDto(order);
            final var message = objectMapper.writeValueAsBytes(orderDto);
            messageSender.send(bindingName, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
