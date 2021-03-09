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
    @JsonProperty("recommend")
    private List<GeneralSimpleItemVO> homeRecommendVO;
    @JsonProperty("new_arrivals")
    private List<GeneralSingleItemVO> homeNewVO;
    @JsonProperty("trending")
    private List<GeneralSingleItemVO> homeTrendingVO;
    @JsonProperty("discount")
    private List<GeneralDiscountItemVO> generalDiscountItemVOS;
    @JsonProperty("accessories")
    private List<GeneralSimpleItemVO> homeAccessoriesVO;
    @JsonProperty("vip_poster")
    private String vipPosterImg;
}
