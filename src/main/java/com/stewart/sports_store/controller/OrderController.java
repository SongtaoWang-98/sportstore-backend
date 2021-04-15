package com.stewart.sports_store.controller;

import com.stewart.sports_store.dto.OrderDTO;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.service.OrderService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO createOrder(@RequestBody OrderDTO orderDTO) {
        StatusCode statusCode = orderService.createOrder(orderDTO);
        if(statusCode.getCode() == 200) {
            return ResultVOUtil.success(statusCode.getMsg());
        }
        else return ResultVOUtil.fail(statusCode.getMsg());
    }

    @PutMapping("/delete")
    public ResultVO deleteOrder(Integer orderId) {
        StatusCode statusCode = orderService.deleteOrder(orderId);
        if(statusCode.getCode() == 200) {
            return ResultVOUtil.success(statusCode.getMsg());
        }
        else return ResultVOUtil.fail(statusCode.getMsg());
    }

    @GetMapping("/view")
    public ResultVO viewOrder() {
        return ResultVOUtil.success(orderService.viewOrder());
    }

}
