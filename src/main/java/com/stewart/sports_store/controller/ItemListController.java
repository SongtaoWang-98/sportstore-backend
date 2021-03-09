package com.stewart.sports_store.controller;

import com.stewart.sports_store.service.ItemListService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/itemList")
public class ItemListController {

    @Autowired
    private ItemListService itemListService;

    @GetMapping("/findByCategory/{group}")
    public ResultVO findByConditions(@PathVariable String group, @RequestParam(defaultValue = "all") String category,
                                     @RequestParam(defaultValue = "all") String style) {
        return ResultVOUtil.success(itemListService.findItemsByCategory(group,category,style));
    }

    @GetMapping("/findByBrand/{brand}")
    public ResultVO findByBrand(@PathVariable String brand) {
        return ResultVOUtil.success(itemListService.findItemsByBrand(brand));
    }

    @GetMapping("/findDiscountByCategory/{group}")
    public ResultVO findByBrand(@PathVariable String group, @RequestParam(defaultValue = "all") String category) {
        return ResultVOUtil.success(itemListService.findDiscountByCategory(group, category));
    }

}
