package com.stewart.sports_store.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer userId;
    private Integer itemsId;
    private Integer paymentId;
    private Date createTime;
    private BigDecimal itemsPrice;
    private String deliveryType;
    private BigDecimal deliveryPrice;
    private String paymentMethod;
}
