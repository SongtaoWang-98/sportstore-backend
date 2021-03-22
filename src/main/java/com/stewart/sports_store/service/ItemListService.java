package com.stewart.sports_store.service;

import com.stewart.sports_store.vo.ItemListVO;

import java.util.List;


public interface ItemListService {
     ItemListVO findItemsByConditions(List<String> groups, List<String> categories, List<String> styles,
                                      List<String> brands, List<String> colors, Boolean discount);
}
