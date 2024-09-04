package com.ecommerce.order.manager.service.impl;

import com.ecommerce.order.manager.broker.OrderEventPublish;
import com.ecommerce.order.manager.model.Order;
import com.ecommerce.order.manager.repository.OrderRepository;
import com.ecommerce.order.manager.service.OrderCreationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreationServiceImpl implements OrderCreationService {

    private final OrderRepository orderRepository;
    private final OrderEventPublish orderEventPublish;

    @Override
    public Order create(Order order) {
        order.calculateTotalValue();
        order = orderRepository.save(order);
        orderEventPublish.orderCreated(order);
        log.info(format("[%s] Pedido Criado", order.getId()));
        return order;
    }
}
