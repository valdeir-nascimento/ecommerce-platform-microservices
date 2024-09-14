package com.ecommerce.order.manager.service.impl;

import com.ecommerce.order.manager.broker.OrderEventPublisher;
import com.ecommerce.order.manager.factory.OrderFactory;
import com.ecommerce.order.manager.model.Order;
import com.ecommerce.order.manager.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderCreationServiceImplTest {

    @InjectMocks
    private OrderCreationServiceImpl orderCreationService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderEventPublisher orderEventPublisher;

    private Order order;

    @BeforeEach
    void setUp() {
        order = OrderFactory.createOrder();
    }

    @Test
    void givenOrder_whenCreateOrder_thenTotalValueIsCalculated() {
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Order createdOrder = orderCreationService.create(order);
        assertEquals(200.0, createdOrder.getTotalAmount());
    }

    @Test
    void givenOrder_whenCreateOrder_thenOrderIsSaved() {
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Order createdOrder = orderCreationService.create(order);
        verify(orderRepository, times(1)).save(order);
        assertNotNull(createdOrder.getId());
    }

    @Test
    void givenOrder_whenCreateOrder_thenEventIsPublished() {
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
        orderCreationService.create(order);
        verify(orderEventPublisher, times(1)).publish(order);
    }

}