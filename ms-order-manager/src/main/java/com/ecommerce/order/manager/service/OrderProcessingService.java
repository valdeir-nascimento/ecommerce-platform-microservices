package com.ecommerce.order.manager.service;

public interface OrderProcessingService {

    void processReservation(String orderId);

    void processQualification(String orderId);

    void processRejection(String orderId);

    void processShipment(String orderId);

    void processDelivery(String orderId);
}
