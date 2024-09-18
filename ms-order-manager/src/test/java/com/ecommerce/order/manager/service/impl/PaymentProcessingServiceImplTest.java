package com.ecommerce.order.manager.service.impl;

import com.ecommerce.abc.commons.enums.OrderStatus;
import com.ecommerce.order.manager.factory.OrderFactory;
import com.ecommerce.order.manager.model.Payment;
import com.ecommerce.order.manager.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentProcessingServiceImplTest {

    @InjectMocks
    private PaymentProcessingServiceImpl paymentProcessingService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void givenAuthorizedPayment_whenProcessAuthorizedPayment_thenOrderStatusIsUpdatedToPreparation() {
        String orderId = "cbab9b0e-b9fd-4e0b-8d34-b9ba2f114537";
        Payment payment = OrderFactory.createPayment();
        paymentProcessingService.processAuthorizedPayment(orderId, payment);
        verify(orderRepository).update(orderId, payment);
        verify(orderRepository).update(orderId, OrderStatus.em_preparacao);
    }

    @Test
    void givenUnauthorizedPayment_whenProcessUnauthorizedPayment_thenOrderStatusIsUpdatedToCancelled() {
        String orderId = "cbab9b0e-b9fd-4e0b-8d34-b9ba2f114537";
        Payment payment = OrderFactory.createPayment();
        paymentProcessingService.processUnauthorizedPayment(orderId, payment);
        verify(orderRepository).update(orderId, payment);
        verify(orderRepository).update(orderId, OrderStatus.cancelado);
    }

}