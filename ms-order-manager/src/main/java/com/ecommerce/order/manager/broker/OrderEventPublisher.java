package com.ecommerce.order.manager.broker;

import com.ecommerce.order.manager.model.Order;

public interface OrderEventPublisher {
    void publish(Order order);
}
