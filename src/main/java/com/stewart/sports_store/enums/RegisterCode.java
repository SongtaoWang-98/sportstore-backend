package com.stewart.sports_store.enums;

import lombok.Getter;

@Getter
public enum RegisterCode {
    SUCCESS(500,"成功"),

    USERNAME_NULL(1001,"用户名为空"),
    TEL_NULL(1002,"电话为空"),
    INVALID_TEL(1002,"电话号码格式有误"),
    PASSWORD_DIFFERENT(1003,"两次密码不同"),

    FAIL(2001,"注册失败");


    private Integer code;
    private String msg;

    RegisterCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
