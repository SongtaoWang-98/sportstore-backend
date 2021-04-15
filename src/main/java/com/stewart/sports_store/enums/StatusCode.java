package com.stewart.sports_store.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
    SUCCESS(200,"成功"),

    INSUFFICIENT_STOCK(1001,"库存不足"),
    ITEM_EXISTS(1002,"该商品已在购物车中"),

    FAIL(2001,"失败");


    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
