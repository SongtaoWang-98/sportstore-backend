package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralSimpleItemVO {
    @JsonProperty("id")
    private Integer itemId;
    @JsonProperty("img")
    private String itemPic1;
}
