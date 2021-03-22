package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.service.ItemListService;
import com.stewart.sports_store.vo.ItemListVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemListServiceImplTest {

    @Autowired
    ItemListService itemListService;

//    @Test
//    void test() {
//        ItemListVO itemListVO = itemListService.findItemsByCategory("men", "shoes", "all");
//        int i = 0;
//    }
}