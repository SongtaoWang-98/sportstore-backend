package com.stewart.sports_store.controller;

import com.stewart.sports_store.service.ItemListService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemList")
public class ItemListController {

    @Autowired
    private ItemListService itemListService;

    @GetMapping()
    public ResultVO findByConditions(String[] groups, String[] categories, String[] styles,
                                     String[] brands, String[] colors, Boolean discount) {
        System.out.println("456");
        List<String> groupList = (groups == null ? null : Arrays.asList(groups));
        List<String> categoryList = (categories == null ? null : Arrays.asList(categories));
        List<String> styleList = (styles == null ? null : Arrays.asList(styles));
        List<String> brandList = (brands == null ? null : Arrays.asList(brands));
        List<String> colorList = (colors == null ? null : Arrays.asList(colors));
        return ResultVOUtil.success(itemListService.findItemsByConditions(groupList, categoryList, styleList,
                brandList, colorList, discount));
    }

}
