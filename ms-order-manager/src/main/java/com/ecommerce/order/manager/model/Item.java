package com.ecommerce.order.manager.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Jacksonized
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String productId;
    private Integer count;
    private Double price;
}
