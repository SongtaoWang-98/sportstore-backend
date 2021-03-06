package com.stewart.sports_store.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ItemCategory {
    @Id
    private Integer itemId;
    private String targetGroup;
    private String categoryName;
    private String usageStyle;
}
