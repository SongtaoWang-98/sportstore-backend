package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {
    List<ItemCategory> findByTargetGroupAndCategoryNameAndUsageStyle(String targetGroup, String categoryName, String usageStyle);
    ItemCategory findByItemId(Integer itemId);
    List<ItemCategory> findByCategoryNameNotIn(Collection<String> categoryName);
    List<ItemCategory> findByCategoryNameIn(Collection<String> categoryName);
    List<ItemCategory> findByTargetGroupIn(Collection<String> targetGroup);
    List<ItemCategory> findByTargetGroupInAndCategoryNameIn(Collection<String> targetGroup, Collection<String> categoryName);
    List<ItemCategory> findByTargetGroupInAndCategoryNameInAndUsageStyleIn(
            Collection<String> targetGroup, Collection<String> categoryName, Collection<String> usageStyle);
}
