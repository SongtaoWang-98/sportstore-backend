package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemDiscountListVO {
    @JsonProperty("items")
    private List<GeneralDiscountItemVO> singleItemVOList;
}