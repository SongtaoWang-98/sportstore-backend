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
    private List<GeneralSingleItemVO> homeRecommendVO;
    @JsonProperty("new_arrivals")
    private List<GeneralSingleItemVO> homeNewVO;
    @JsonProperty("trending")
    private List<GeneralSingleItemVO> homeTrendingVO;
    @JsonProperty("discount")
    private List<GeneralDiscountItemVO> generalDiscountItemVOS;
    @JsonProperty("accessories")
    private List<GeneralSingleItemVO> homeAccessoriesVO;
    @JsonProperty("vip_poster")
    private String vipPosterImg;
}
