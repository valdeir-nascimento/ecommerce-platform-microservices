package com.ecommerce.order.manager.model;

import com.ecommerce.abc.commons.enums.Brand;
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
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cardId, bin, numberToken, cardholderName, securityCode, expirationMonth, expirationYear;

    private Brand brand;
}
