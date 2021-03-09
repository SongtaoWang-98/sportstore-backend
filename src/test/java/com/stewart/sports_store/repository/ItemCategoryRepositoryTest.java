package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemCategoryRepositoryTest {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Test
    void findAll() {
//        itemCategoryRepository.findAll();
    }

    @Test
    void findTest() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"shoes","clothing");
        List<ItemCategory> categoryNameNotIn = itemCategoryRepository.findByCategoryNameNotIn(list);
        for(ItemCategory category: categoryNameNotIn) {
            System.out.println(category);
        }
    }

    @Test
    void find() {
        List<ItemCategory> categories = itemCategoryRepository.findByTargetGroupAndCategoryNameAndUsageStyle("男子","鞋类", "跑鞋");
        for(ItemCategory category: categories) {
            System.out.println(category);
        }
    }
}