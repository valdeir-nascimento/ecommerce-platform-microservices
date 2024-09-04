package com.ecommerce.order.manager.broker;

import com.ecommerce.order.manager.model.Order;

public interface OrderEventPublish {

    void orderCreated(Order order);

    void orderConfirmed(Order order);

}
