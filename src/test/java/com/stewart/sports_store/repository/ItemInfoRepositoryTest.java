package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.ItemInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemInfoRepositoryTest {

    @Autowired
    private ItemInfoRepository repository;

    @Test
    void findAll() {
        List<ItemInfo> list = repository.findAll();
        for(ItemInfo itemInfo: list) {
            System.out.println(itemInfo);
        }
    }

    @Test
    void findByItemId() {
        ItemInfo itemInfo = repository.findByItemId(2);
        System.out.println(itemInfo);
    }

}