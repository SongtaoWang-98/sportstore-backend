package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemAttributeRepositoryTest {
    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Test
    void findall() {
        List<ItemAttribute> list = itemAttributeRepository.findAll();
        for(ItemAttribute itemAttribute: list) {
            System.out.println(itemAttribute);
        }
    }

    @Test
    void find2() {
        List<ItemAttribute> list = itemAttributeRepository.getByOrderByNumberSaleDesc();
        for(ItemAttribute itemAttribute: list) {
            System.out.println(itemAttribute);
        }
    }

}