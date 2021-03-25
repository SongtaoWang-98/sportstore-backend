package com.stewart.sports_store.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class OrderInfo {
    @Id
    private Integer orderId;
    private Integer itemId;
    private Integer userId;
    private Date createTime;
    private String itemName;
    private BigDecimal itemPrice;
    private String paymentAddress;
    private String paymentName;
    private String paymentTel;
}
