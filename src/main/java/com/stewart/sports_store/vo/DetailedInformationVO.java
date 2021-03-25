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
    @JsonProperty("previous_price")
    private BigDecimal previousPrice;
    @JsonProperty("stock")
    private Integer numberStock;
    @JsonProperty("sale")
    private Integer numberSale;
    @JsonProperty("brand")
    private String itemBrand;
    @JsonProperty("rating")
    private Double itemRating;
    @JsonProperty("related_items")
    private List<GeneralSingleItemVO> relatedItems;
}
