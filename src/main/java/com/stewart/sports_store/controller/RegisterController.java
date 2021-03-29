package com.stewart.sports_store.controller;

import com.stewart.sports_store.dto.RegisterDTO;
import com.stewart.sports_store.enums.RegisterCode;
import com.stewart.sports_store.service.RegisterService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/new")
    public ResultVO register(@RequestBody RegisterDTO registerDTO) {
        RegisterCode result = registerService.register(registerDTO);
        if(result.getCode() == 200) return ResultVOUtil.success(result);
        else return ResultVOUtil.fail(result);
    }
}
