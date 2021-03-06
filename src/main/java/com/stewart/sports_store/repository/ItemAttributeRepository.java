package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.ItemAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemAttributeRepository extends JpaRepository<ItemAttribute,Integer> {
    ItemAttribute findByItemId(Integer itemId);
    List<ItemAttribute> getByOrderByUpdateTimeDesc();
    List<ItemAttribute> getByOrderByNumberSaleDesc();
    List<ItemAttribute> findByPreviousPriceIsNotNull();
}
