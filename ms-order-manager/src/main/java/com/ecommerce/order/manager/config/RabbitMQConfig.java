package com.ecommerce.order.manager.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.cloud.stream.bindings.pedidoCriado-out-0.destination}")
    private String orderCreatedQueue;

    @Value("${spring.cloud.stream.bindings.pedidoConfirmado-out-0.destination}")
    private String orderConfirmedQueue;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public FanoutExchange orderCreatedExchange() {
        return new FanoutExchange(orderCreatedQueue);
    }

    @Bean
    public FanoutExchange orderConfirmedExchange() {
        return new FanoutExchange(orderConfirmedQueue);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
