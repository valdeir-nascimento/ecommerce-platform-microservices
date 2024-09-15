package com.ecommerce.order.manager.service.impl;

import com.ecommerce.abc.commons.enums.OrderStatus;
import com.ecommerce.order.manager.broker.OrderEventPublisher;
import com.ecommerce.order.manager.repository.OrderRepository;
import com.ecommerce.order.manager.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProcessingServiceImpl implements OrderProcessingService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    @Override
    public void processReservation(String orderId) {
        final var order = orderRepository.reserve(orderId);
        log.info(format("[%s] Pedido Reservado", orderId));
        if (order.getQualified()) {
            confirmOrder(orderId);
        }
    }

    @Override
    public void processQualification(String orderId) {
        final var order = orderRepository.qualify(orderId);
        log.info(format("[%s] Pedido Qualificado", orderId));
        if (order.getQualified()) {
            confirmOrder(orderId);
        }
    }

    @Override
    public void processRejection(String orderId) {
        orderRepository.update(orderId, OrderStatus.cancelado);
        log.info(format("[%s] Pedido Cancelado", orderId));
    }

    @Override
    public void processShippment(String orderId) {
        orderRepository.update(orderId, OrderStatus.enviado);
        log.info(format("[%s] Pedido Enviado", orderId));
    }

    @Override
    public void processDelivery(String orderId) {
        orderRepository.update(orderId, OrderStatus.entregue);
        log.info(format("[%s] Pedido Entregue", orderId));
    }

    private void confirmOrder(String orderId) {
        final var order = orderRepository.update(orderId, OrderStatus.confirmado);
        orderEventPublisher.orderConfimed(order);
        log.info(format("[%s] Pedido Confirmado", orderId));
    }
}
