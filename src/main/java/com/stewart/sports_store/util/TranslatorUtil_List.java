package com.stewart.sports_store.util;

import java.util.ArrayList;
import java.util.List;

public class TranslatorUtil_List {
    public static List<String> TransList(List<String> engList) {
        List<String> cnList = new ArrayList<>();
        for(String s: engList) {
            cnList.add(TranslatorUtil.TransEnToCh(s));
        }
        return cnList;
    }
}
