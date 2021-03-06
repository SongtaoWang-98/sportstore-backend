package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void findByTargetGroup() {
        List<ItemCategory> list = itemCategoryRepository.findByTargetGroup("men");
        for(ItemCategory itemCategory: list) {
            System.out.println(itemCategory);
        }
    }
}