package com.stewart.sports_store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer[] cartIds;
    private Integer paymentId;
    private String deliveryType;
    private String paymentMethod;
}
