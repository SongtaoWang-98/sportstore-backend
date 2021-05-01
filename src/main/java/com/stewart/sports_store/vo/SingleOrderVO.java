package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleOrderVO {
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("items")
    private List<SingleItemInOrderVO> singleItemInOrderVOList;
    @JsonProperty("items_price")
    private BigDecimal itemsPrice;
    @JsonProperty("delivery_type")
    private String deliveryType;
    @JsonProperty("delivery_price")
    private BigDecimal deliveryPrice;
    @JsonProperty("total_price")
    private BigDecimal totalPrice;
    @JsonProperty("create_time")
    private String createTime;
    @JsonProperty("arrival_time")
    private String arrivalTime;
    @JsonProperty("status")
    private String currentStatus;
}
