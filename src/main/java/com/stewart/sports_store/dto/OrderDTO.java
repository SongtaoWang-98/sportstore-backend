package com.stewart.sports_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private List<OrderItemDTO> itemDTOS;
    private Integer paymentId;
    private Date createTime;
    private BigDecimal itemsPrice;
    private String deliveryType;
    private BigDecimal deliveryPrice;
    private String paymentMethod;
}
