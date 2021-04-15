package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleItemInOrderVO {
    @JsonProperty("item_id")
    private Integer itemId;
    @JsonProperty("brand")
    private String itemBrand;
    @JsonProperty("name")
    private String itemName;
    @JsonProperty("img")
    private String itemPic1;
    @JsonProperty("num")
    private Integer itemNum;
    @JsonProperty("current_price")
    private BigDecimal currentPrice;
}
