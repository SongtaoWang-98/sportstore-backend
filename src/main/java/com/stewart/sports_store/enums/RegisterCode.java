package com.stewart.sports_store.enums;

import lombok.Getter;

@Getter
public enum RegisterCode {
    SUCCESS(200,"成功"),

    USERNAME_EXISTS(1001,"用户名已存在"),
    TEL_EXISTS(1002,"手机号已被注册"),

    FAIL(2001,"注册失败");


    private Integer code;
    private String msg;

    RegisterCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
