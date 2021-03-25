package com.stewart.sports_store.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserInfo {
    @Id
    private Integer userId;
    private String userName;
    private String userTel;
    private String userPassword;
}
