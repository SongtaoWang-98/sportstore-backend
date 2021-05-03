package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;

@Data
public class CartVO {
    @JsonProperty("items")
    private List<SingleCartVO> cartItemVOList;
    @JsonProperty("recommend")
    private List<GeneralSingleItemVO> generalSingleItemVOList;
    @JsonProperty("total_price")
    private BigDecimal totalPrice;
}
