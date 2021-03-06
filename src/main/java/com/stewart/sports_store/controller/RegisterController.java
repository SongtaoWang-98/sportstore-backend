package com.stewart.sports_store.controller;

import com.stewart.sports_store.dto.RegisterDTO;
import com.stewart.sports_store.enums.RegisterCode;
import com.stewart.sports_store.service.RegisterService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResultVO register(@RequestBody RegisterDTO infoForm) {
        RegisterCode result = registerService.register(infoForm);
        if(result.getCode() == 200) {
            return ResultVOUtil.success(result.getMsg());
        }
        else {
            System.out.println(ResultVOUtil.fail(result.getMsg()));
            return ResultVOUtil.fail(result.getMsg());
        }
    }
}
