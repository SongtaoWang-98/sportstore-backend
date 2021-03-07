package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class GeneralSingleItemVO {
    @JsonProperty("id")
    private Integer itemId;
    @JsonProperty("brand")
    private String itemBrand;
    @JsonProperty("name")
    private String itemName;
    @JsonProperty("img")
    private String itemPic1;
    @JsonProperty("price")
    private BigDecimal currentPrice;
    @JsonProperty("group")
    private String targetGroup;
    @JsonProperty("style")
    private String usageStyle;
}
