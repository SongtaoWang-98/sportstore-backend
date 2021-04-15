package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaymentInfoVO {
    @JsonProperty("info")
    List<SinglePaymentInfoVO> singlePaymentInfoVOList;
}
