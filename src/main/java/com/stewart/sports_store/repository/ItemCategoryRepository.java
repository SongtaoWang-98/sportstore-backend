package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {
    List<ItemCategory> findByTargetGroup(String targeGroup);
    List<ItemCategory> findByTargetGroupAndCategoryName(String targetGroup, String categoryName);
    List<ItemCategory> findByTargetGroupAndCategoryNameAndUsageStyle(String targetGroup, String categoryName, String usateStyle);
    ItemCategory findByItemId(Integer itemId);
    List<ItemCategory> findByCategoryNameNotIn(Collection<String> categoryName);
}
