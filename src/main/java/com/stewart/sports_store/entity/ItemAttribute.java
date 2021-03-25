package com.stewart.sports_store.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class ItemAttribute {
    @Id
    private Integer itemId;
    private String itemColor;
    private String itemSize;
    private BigDecimal currentPrice;
    private BigDecimal previousPrice;
    private Date updateTime;
    private Integer numberStock;
    private Integer numberSale;
    private String itemBrand;
}
