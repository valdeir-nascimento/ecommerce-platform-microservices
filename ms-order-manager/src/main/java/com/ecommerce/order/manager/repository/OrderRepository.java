package com.ecommerce.order.manager.repository;

import com.ecommerce.order.manager.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String>, CustomOrderRepository {
}
