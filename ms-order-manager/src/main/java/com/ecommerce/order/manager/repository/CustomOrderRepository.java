package com.ecommerce.order.manager.repository;

import com.ecommerce.abc.commons.enums.OrderStatus;
import com.ecommerce.order.manager.model.Order;
import com.ecommerce.order.manager.model.Payment;

public interface CustomOrderRepository {

    Order update(String orderId, OrderStatus status);

    Order update(String orderId, Payment payment);

    Order reserve(String orderId);

    Order qualify(String orderId);

}
