package com.ecommerce.order.manager.repository.impl;

import com.ecommerce.abc.commons.enums.OrderStatus;
import com.ecommerce.order.manager.model.Order;
import com.ecommerce.order.manager.model.Payment;
import com.ecommerce.order.manager.repository.CustomOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Order update(String orderId, OrderStatus status) {
        return update(orderId, "status", status);
    }

    @Override
    public Order update(String orderId, Payment payment) {
        return update(orderId, "payment", payment);
    }

    @Override
    public Order reserve(String orderId) {
        return update(orderId, "reserved", true);
    }

    @Override
    public Order qualify(String orderId) {
        return update(orderId, "qualified", true);
    }

    private Order update(String orderId, String field, Object value) {
        return mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(orderId)),
                new Update().set(field, value), Order.class);
    }
}
