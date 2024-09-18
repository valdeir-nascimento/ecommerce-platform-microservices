package com.ecommerce.order.manager.service.impl;

import com.ecommerce.abc.commons.enums.OrderStatus;
import com.ecommerce.order.manager.model.Payment;
import com.ecommerce.order.manager.repository.OrderRepository;
import com.ecommerce.order.manager.service.PaymentProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentProcessingServiceImpl implements PaymentProcessingService {

    private final OrderRepository orderRepository;

    @Override
    public void processAuthorizedPayment(String orderId, Payment payment) {
        orderRepository.update(orderId, payment);
        orderRepository.update(orderId, OrderStatus.em_preparacao);
        log.info(format("[%s] Pagamento Autorizado :)", orderId));
    }

    @Override
    public void processUnauthorizedPayment(String orderId, Payment payment) {
        orderRepository.update(orderId, payment);
        orderRepository.update(orderId, OrderStatus.cancelado);
        log.info(format("[%s] Pagamento Não Autorizado :(", orderId));
    }

}
