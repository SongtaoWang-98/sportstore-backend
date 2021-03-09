package com.stewart.sports_store.service;

import com.stewart.sports_store.vo.ItemDiscountListVO;
import com.stewart.sports_store.vo.ItemListVO;


public interface ItemListService {
    public ItemListVO findItemsByCategory(String group, String category, String style);
    public ItemListVO findItemsByBrand(String brand);
    public ItemDiscountListVO findDiscountByCategory(String group, String category);
}
