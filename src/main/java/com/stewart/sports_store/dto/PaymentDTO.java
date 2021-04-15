package com.stewart.sports_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String familyName;
    private String givenName;
    private String province;
    private String city;
    private String district;
    private String address;
    private String tel;
}
