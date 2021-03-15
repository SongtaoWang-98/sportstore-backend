package com.stewart.sports_store.controller;

import com.stewart.sports_store.service.ItemListFilterService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemListFilter")
public class ItemListFilterController {

    @Autowired
    private ItemListFilterService itemListFilterService;

    @GetMapping("/filterByConditions")
    public ResultVO filterByConditions(String[] groups, String[] categories, String[] brands, String[] colors) {
        System.out.println("456");
        List<String> groupList = Arrays.asList(groups);
        List<String> categoryList = Arrays.asList(categories);
        List<String> brandList = Arrays.asList(brands);
        List<String> colorList = Arrays.asList(colors);
        return ResultVOUtil.success(itemListFilterService.filterItemsByConditions(groupList, categoryList, brandList, colorList));
    }
}
