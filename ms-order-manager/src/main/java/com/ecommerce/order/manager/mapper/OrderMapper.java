package com.ecommerce.order.manager.mapper;

import com.ecommerce.abc.commons.dto.OrderCreationDto;
import com.ecommerce.abc.commons.dto.OrderDto;
import com.ecommerce.abc.commons.dto.PaymentDto;
import com.ecommerce.order.manager.model.Order;
import com.ecommerce.order.manager.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface OrderMapper {

    OrderDto toDto(Order order);

    @Mappings({
            @Mapping(source = "items", target = "items"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "payment", target = "payment"),
    })
    Order toEntity(OrderCreationDto orderDto);

    Payment toPayment(PaymentDto paymentDto);

}
