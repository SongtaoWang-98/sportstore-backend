package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.service.ItemListFilterService;
import com.stewart.sports_store.vo.ItemListVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemListFilterServiceImplTest {
    @Autowired
    ItemListFilterService itemListFilterService;

    @Test
    void test() {
        List<String> groupList = new ArrayList<>();
        groupList.add("men");
        List<String> categoryList = new ArrayList<>();
        categoryList.add("shoes");
        List<String> brandList = new ArrayList<>();
        brandList.add("Nike");
        List<String> colorList = new ArrayList<>();
        colorList.add("black");
        colorList.add("white");

        ItemListVO itemListVO = itemListFilterService.filterItemsByConditions(groupList, categoryList, brandList, colorList);
        int i = 0;
    }

}