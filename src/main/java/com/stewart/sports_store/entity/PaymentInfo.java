package com.stewart.sports_store.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    private Integer userId;
    private String familyName;
    private String givenName;
    private String province;
    private String city;
    private String district;
    private String detailedAddress;
    private String paymentTel;
}
