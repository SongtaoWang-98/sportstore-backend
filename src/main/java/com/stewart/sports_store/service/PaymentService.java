package com.stewart.sports_store.service;

import com.stewart.sports_store.dto.PaymentDTO;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.vo.PaymentInfoVO;
import com.stewart.sports_store.vo.PayingItemsVO;

import java.util.List;

public interface PaymentService {
    StatusCode addPaymentInfo(PaymentDTO paymentDTO);
    StatusCode deletePaymentInfo(Integer paymentId);
    PaymentDTO choosePaymentInfo(Integer paymentId);
    StatusCode updatePaymentInfo(Integer paymentId, PaymentDTO paymentDTO);
    PaymentInfoVO showPaymentInfo();
    PayingItemsVO showPayingItems(List<Integer> cartIdList);
}
