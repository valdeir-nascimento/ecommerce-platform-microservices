package com.ecommerce.order.manager.broker.message;

public interface MessageSender {
    void send(String bindingName, byte[] message);
}