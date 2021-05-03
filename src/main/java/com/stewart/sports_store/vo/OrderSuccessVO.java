package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSuccessVO {
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("is_paid")
    private Boolean isPaid;
    @JsonProperty("payment_method")
    private String paymentMethod;
    @JsonProperty("total_price")
    private BigDecimal totalPrice;
    @JsonProperty("address")
    private String paymentAddress;
    @JsonProperty("delivery_type")
    private String deliveryType;
    @JsonProperty("related_items")
    private List<GeneralSingleItemVO> relatedItems;
}
