package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemInfoRepository extends JpaRepository<ItemInfo, Integer> {
    ItemInfo findByItemId(Integer itemId);
    long count();
}
