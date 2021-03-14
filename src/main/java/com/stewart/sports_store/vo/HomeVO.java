package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HomeVO {
    @JsonProperty("poster")
    private String posterImg;
    @JsonProperty("trending")
    private List<GeneralSingleItemVO> homeTrendingVO;
    @JsonProperty("new_arrivals")
    private List<GeneralSimpleItemVO> homeNewVO;
    @JsonProperty("discount")
    private List<GeneralSingleItemVO> generalDiscountItemVOS;
    @JsonProperty("recommend")
    private List<GeneralSimpleItemVO> homeRecommendVO;
    @JsonProperty("accessories")
    private List<GeneralSimpleItemVO> homeAccessoriesVO;
    @JsonProperty("vip_poster")
    private String vipPosterImg;
}
