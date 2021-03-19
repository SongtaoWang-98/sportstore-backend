package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemListVO {
    @JsonProperty("items_number")
    Integer number;
    @JsonProperty("items")
    private List<GeneralDetailedItemVO> detailedItemVOList;
}
