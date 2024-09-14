package com.ecommerce.order.manager.factory;

import com.ecommerce.abc.commons.enums.Brand;
import com.ecommerce.abc.commons.enums.OrderStatus;
import com.ecommerce.order.manager.model.Customer;
import com.ecommerce.order.manager.model.Item;
import com.ecommerce.order.manager.model.Order;
import com.ecommerce.order.manager.model.Payment;

import java.util.List;
import java.util.UUID;

public class OrderFactory {

    private OrderFactory() {}

    public static Order createOrder() {
        return Order.builder()
                .id(UUID.randomUUID().toString())
                .customer(createCustomer())
                .payment(createPayment())
                .status(OrderStatus.recebido)
                .items(createItems())
                .build();
    }

    public static Customer createCustomer() {
        return Customer.builder()
                .id(UUID.randomUUID().toString())
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();
    }

    public static Payment createPayment() {
        return Payment.builder()
                .cardId("card123")
                .bin("123456")
                .brand(Brand.Visa)
                .build();
    }

    public static List<Item> createItems() {
        Item prod1 = Item.builder().productId("prod1")
                .price(100.0)
                .count(1)
                .build();
        Item prod2 = Item.builder().productId("prod2")
                .price(50.0)
                .count(2)
                .build();
        return List.of(prod1, prod2);
    }
}
