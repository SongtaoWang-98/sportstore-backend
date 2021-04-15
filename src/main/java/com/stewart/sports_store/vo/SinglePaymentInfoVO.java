package com.stewart.sports_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SinglePaymentInfoVO {
    @JsonProperty("payment_id")
    private Integer paymentId;
    @JsonProperty("name")
    private String paymentName;
    @JsonProperty("address")
    private String paymentAddress;
    @JsonProperty("tel")
    private String paymentTel;
}
