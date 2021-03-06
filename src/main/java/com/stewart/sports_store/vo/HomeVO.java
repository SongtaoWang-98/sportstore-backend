package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class HomeVO {
    @JsonProperty("classification")
    private List<GroupVO> classificationVOS;
    @JsonProperty("poster")
    private String posterImg;
    @JsonProperty("recommend")
    private List<HomeRecommendVO> homeRecommendVOS;
    @JsonProperty("new")
    private List<HomeNewVO> homeNewVOS;
    @JsonProperty("trending")
    private List<HomeTrendingVO> homeTrendingVOS;
    @JsonProperty("discount")
    private List<HomeDiscountVO> homeDiscountVOS;
    @JsonProperty("group")
    private List<HomeSimpleGroupVO> homeSimpleGroupVOS;
}
