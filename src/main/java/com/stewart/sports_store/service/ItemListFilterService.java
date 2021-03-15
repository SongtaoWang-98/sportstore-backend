package com.stewart.sports_store.service;

import com.stewart.sports_store.vo.ItemListVO;

import java.util.List;

public interface ItemListFilterService {
    ItemListVO filterItemsByConditions(List<String> groups, List<String> categories, List<String> brands,
                                       List<String> colors);
}
