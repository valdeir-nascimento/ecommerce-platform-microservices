package com.ecommerce.order.manager.service.impl;

import com.ecommerce.abc.commons.enums.OrderStatus;
import com.ecommerce.order.manager.broker.OrderEventPublisher;
import com.ecommerce.order.manager.model.Order;
import com.ecommerce.order.manager.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderProcessingServiceImplTest {

    @InjectMocks
    private OrderProcessingServiceImpl orderProcessingService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderEventPublisher orderEventPublisher;

    @Test
    void givenQualifiedOrder_whenProcessReservation_thenOrderIsConfirmed() {
        String orderId = "cbab9b0e-b9fd-4e0b-8d34-b9ba2f114537";
        Order order = mock(Order.class);
        when(orderRepository.reserve(orderId)).thenReturn(order);
        when(order.getQualified()).thenReturn(Boolean.TRUE);
        when(orderRepository.update(orderId, OrderStatus.confirmado)).thenReturn(order);

        orderProcessingService.processReservation(orderId);

        verify(orderRepository).reserve(orderId);
        verify(orderRepository).update(orderId, OrderStatus.confirmado);
        verify(orderEventPublisher, times(1)).orderConfimed(order);
    }

    @Test
    void givenUnqualifiedOrder_whenProcessReservation_thenOrderIsNotConfirmed() {
        String orderId = "cbab9b0e-b9fd-4e0b-8d34-b9ba2f114537";
        Order order = mock(Order.class);
        when(orderRepository.reserve(orderId)).thenReturn(order);
        when(order.getQualified()).thenReturn(Boolean.FALSE);

        orderProcessingService.processReservation(orderId);

        verify(orderRepository).reserve(orderId);
        verify(orderRepository, never()).update(orderId, OrderStatus.confirmado);
        verify(orderEventPublisher, never()).orderConfimed(any());
    }

    @Test
    void givenQualifiedOrder_whenProcessQualification_thenOrderIsConfirmed() {
        String orderId = "cbab9b0e-b9fd-4e0b-8d34-b9ba2f114537";
        Order order = mock(Order.class);
        when(orderRepository.qualify(orderId)).thenReturn(order);
        when(order.getQualified()).thenReturn(Boolean.TRUE);
        when(orderRepository.update(orderId, OrderStatus.confirmado)).thenReturn(order);

        orderProcessingService.processQualification(orderId);

        verify(orderRepository).qualify(orderId);
        verify(orderRepository).update(orderId, OrderStatus.confirmado);
        verify(orderEventPublisher).orderConfimed(order);
    }

    @Test
    void givenUnQualifiedOrder_whenProcessQualification_thenOrderIsNotConfirmed() {
        String orderId = "cbab9b0e-b9fd-4e0b-8d34-b9ba2f114537";
        Order order = mock(Order.class);
        when(orderRepository.qualify(orderId)).thenReturn(order);
        when(order.getQualified()).thenReturn(Boolean.FALSE);

        orderProcessingService.processQualification(orderId);

        verify(orderRepository).qualify(orderId);
        verify(orderRepository, never()).update(orderId, OrderStatus.confirmado);
        verify(orderEventPublisher, never()).orderConfimed(any());
    }

    @Test
    void givenOrder_whenProcessRejection_thenOrderStatusIsUpdatedToCancelled() {
        String orderId = "cbab9b0e-b9fd-4e0b-8d34-b9ba2f114537";
        orderProcessingService.processRejection(orderId);
        verify(orderRepository).update(orderId, OrderStatus.cancelado);
    }

    @Test
    void givenOrder_whenProcessShipment_thenOrderStatusIsUpdatedToSend() {
        String orderId = "cbab9b0e-b9fd-4e0b-8d34-b9ba2f114537";
        orderProcessingService.processShipment(orderId);
        verify(orderRepository).update(orderId, OrderStatus.enviado);
    }

    @Test
    void givenOrder_whenProcessDelivery_thenOrderStatusIsUpdatedToDelivery() {
        String orderId = "cbab9b0e-b9fd-4e0b-8d34-b9ba2f114537";
        orderProcessingService.processDelivery(orderId);
        verify(orderRepository).update(orderId, OrderStatus.entregue);
    }

}