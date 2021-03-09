package com.stewart.sports_store.controller;

import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.service.HomeService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping
    public ResultVO index() {
        System.out.println("第一次打印sql");
        return ResultVOUtil.success(homeService.findHomeVO());
    }

}
