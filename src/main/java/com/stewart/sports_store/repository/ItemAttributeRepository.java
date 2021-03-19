package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.ItemAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


public interface ItemAttributeRepository extends JpaRepository<ItemAttribute,Integer> {
    ItemAttribute findByItemId(Integer itemId);
    List<ItemAttribute> getByOrderByUpdateTimeDesc();
    List<ItemAttribute> getByOrderByNumberSaleDesc();
    List<ItemAttribute> findByPreviousPriceIsNotNull();
    List<ItemAttribute> findByItemBrand(String itemBrand);
    List<ItemAttribute> findByItemBrandIn(Collection<String> itemBrand);
    List<ItemAttribute> findByItemColorIn(Collection<String> itemColor);
    List<ItemAttribute> findByItemBrandInAndItemColorIn(Collection<String> itemBrand, Collection<String> itemColor);
}
