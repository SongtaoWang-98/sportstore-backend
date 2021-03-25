package com.stewart.sports_store.controller;

import com.stewart.sports_store.service.ItemInfoService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/itemInfo")
public class ItemInfoController {

    @Autowired
    private ItemInfoService itemInfoService;

    @GetMapping("/{id}")
    public ResultVO itemInformation(@PathVariable int id) {
        return ResultVOUtil.success(itemInfoService.showInformationById(id));
    }
}