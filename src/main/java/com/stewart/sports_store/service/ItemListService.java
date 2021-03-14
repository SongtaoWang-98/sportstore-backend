package com.stewart.sports_store.service;

import com.stewart.sports_store.vo.ItemListVO;


public interface ItemListService {
     ItemListVO findItemsByCategory(String group, String category, String style);
     ItemListVO findItemsByBrand(String brand);
     ItemListVO findDiscountByCategory(String group, String category);
}
