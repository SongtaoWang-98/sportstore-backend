package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.List;

@Data
public class CartVO {
    @JsonProperty("items")
    private List<SingleCartVO> cartItemVOList;
    @JsonProperty("recommend")
    private List<GeneralSimpleItemVO> generalSimpleItemVOList;
}
