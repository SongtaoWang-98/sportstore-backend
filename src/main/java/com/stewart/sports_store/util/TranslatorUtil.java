package com.stewart.sports_store.util;

public class TranslatorUtil {
    public static String TransEnToCh(String word) {
        switch (word) {
            case "men": return "男子";
            case "women": return "女子";
            case "kids": return "儿童";

            case "shoes": return "鞋类";
            case "clothing": return "服装";
            case "accessories": return "配件";

            case "running_shoes": return "跑鞋";
            case "basketball_shoes": return "篮球鞋";
            case "football_shoes": return "足球鞋";
            case "lifestyle_shoes": return "休闲鞋";
            case "slippers": return "拖鞋";

            case "sweatshirt": return "卫衣";
            case "jacket": return "外套";
            case "sportswear": return "运动服";
            case "T-shirt": return "T恤";
            case "trousers": return "休闲裤";
            case "shorts": return "短裤";

            case "bag": return "包";
            case "hat": return "帽子";
            case "socks": return "袜子";
            case "others": return "其他";

            case "Adidas": return "Adidas";
            case "Nike": return "Nike";
            case "Lining": return "李宁";
            case "Anta": return "安踏";
            case "Converse": return"匡威";

            default: return "无";
        }
    }
}
