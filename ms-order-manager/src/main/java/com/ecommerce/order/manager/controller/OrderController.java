package com.ecommerce.order.manager.controller;

import com.ecommerce.abc.commons.dto.OrderCreationDto;
import com.ecommerce.abc.commons.dto.OrderDto;
import com.ecommerce.order.manager.mapper.OrderMapper;
import com.ecommerce.order.manager.service.OrderCreationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class OrderController {

    private final OrderCreationService orderCreationService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderCreationDto orderCreationDto) {
        final var response = orderCreationService.create(orderMapper.toEntity(orderCreationDto));
        return ResponseEntity.ok(orderMapper.toDto(response));
    }

}
