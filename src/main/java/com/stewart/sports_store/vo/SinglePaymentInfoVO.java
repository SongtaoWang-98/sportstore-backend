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
    @JsonProperty("address1")
    private String paymentAddress1;
    @JsonProperty("address2")
    private String paymentAddress2;
    @JsonProperty("tel")
    private String paymentTel;
}
