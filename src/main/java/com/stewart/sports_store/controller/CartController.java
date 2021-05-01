package com.stewart.sports_store.controller;

import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.service.CartService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResultVO addToCart(Integer itemId) {
        StatusCode statusCode = cartService.addItem(itemId);
        if(statusCode.getCode() == 200) {
            return ResultVOUtil.success(statusCode.getMsg());
        }
        else {
            return ResultVOUtil.fail(statusCode.getMsg());
        }
    }

    @PutMapping("/delete")
    public ResultVO deleteInCart(Integer cartId) {
        return ResultVOUtil.success(cartService.deleteItem(cartId));
    }

    @PutMapping("/deleteAll")
    public ResultVO deleteAll() {
        return ResultVOUtil.success(cartService.deleteAll());
    }

    @PutMapping("/update")
    public ResultVO updateInCart(Integer cartId, Integer num) {
        StatusCode statusCode = cartService.updateItem(cartId, num);
        if(statusCode.getCode() == 200) {
            return ResultVOUtil.success(statusCode.getMsg());
        }
        else {
            return ResultVOUtil.fail(statusCode.getMsg());
        }
    }

    @GetMapping
    public ResultVO viewMyCart() {
        return ResultVOUtil.success(cartService.viewCart());
    }

}
