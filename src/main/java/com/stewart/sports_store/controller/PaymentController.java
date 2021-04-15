package com.stewart.sports_store.controller;

import com.stewart.sports_store.dto.PaymentDTO;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.service.PaymentService;
import com.stewart.sports_store.util.ResultVOUtil;
import com.stewart.sports_store.vo.PaymentInfoVO;
import com.stewart.sports_store.vo.ResultVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/add")
    public ResultVO addPaymentInfo(@RequestBody PaymentDTO paymentDTO) {
        StatusCode statusCode = paymentService.addPaymentInfo(paymentDTO);
        if(statusCode.getCode() == 200) {
            return ResultVOUtil.success(statusCode.getMsg());
        }
        else return ResultVOUtil.fail(statusCode.getMsg());
    }

    @PutMapping("/delete")
    public ResultVO deletePaymentInfo(Integer paymentId) {
        StatusCode statusCode = paymentService.deletePaymentInfo(paymentId);
        if(statusCode.getCode() == 200) {
            return ResultVOUtil.success(statusCode.getMsg());
        }
        else return ResultVOUtil.fail(statusCode.getMsg());
    }

    @GetMapping("/choose")
    public ResultVO choosePaymentInfo(Integer paymentId) {
        return ResultVOUtil.success(paymentService.choosePaymentInfo(paymentId));
    }

    @PutMapping("/update")
    public ResultVO updatePaymentInfo(Integer paymentId, @RequestBody PaymentDTO paymentDTO) {
        StatusCode statusCode = paymentService.updatePaymentInfo(paymentId, paymentDTO);
        if(statusCode.getCode() == 200) {
            return ResultVOUtil.success(statusCode.getMsg());
        }
        else return ResultVOUtil.fail(statusCode.getMsg());
    }

    @GetMapping
    public ResultVO showPaymentList() {
        PaymentInfoVO paymentInfoVO = paymentService.showPaymentInfo();
        return ResultVOUtil.success(paymentInfoVO);
    }
}
