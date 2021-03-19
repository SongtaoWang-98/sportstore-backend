package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedInformationVO {
    @JsonProperty("id")
    private Integer itemId;
    @JsonProperty("name")
    private String itemName;
    @JsonProperty("poster_img")
    private String itemPic1;
    @JsonProperty("detail_img1")
    private String itemPic2;
    @JsonProperty("detail_img2")
    private String itemPic3;
    @JsonProperty("group")
    private String targetGroup;
    @JsonProperty("category")
    private String category_name;
    @JsonProperty("style")
    private String usageStyle;
    @JsonProperty("color")
    private String itemColor;
    @JsonProperty("size")
    private String itemSize;
    @JsonProperty("price")
    private BigDecimal currentPrice;
    @JsonProperty("previousPrice")
    private BigDecimal previousPrice;
    @JsonProperty("brand")
    private String itemBrand;
}
