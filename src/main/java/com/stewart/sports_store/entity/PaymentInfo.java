package com.stewart.sports_store.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PaymentInfo {
    @Id
    private Integer paymentId;
    private Integer userId;
    private String paymentTel;
    private String paymentName;
    private String paymentAddress;
}
