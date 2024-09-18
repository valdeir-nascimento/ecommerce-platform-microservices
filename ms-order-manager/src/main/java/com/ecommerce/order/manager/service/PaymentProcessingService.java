package com.ecommerce.order.manager.service;

import com.ecommerce.order.manager.model.Payment;

public interface PaymentProcessingService {

    void processAuthorizedPayment(String orderId, Payment payment);

    void processUnauthorizedPayment(String orderId, Payment payment);

}
