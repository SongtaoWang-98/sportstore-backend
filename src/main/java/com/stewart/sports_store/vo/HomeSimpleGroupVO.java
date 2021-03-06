package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomeSimpleGroupVO {
    @JsonProperty("name")
    private String groupName;
    @JsonProperty("img")
    private String groupImg;
}
