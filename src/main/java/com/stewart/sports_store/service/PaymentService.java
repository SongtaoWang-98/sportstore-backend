package com.stewart.sports_store.service;

import com.stewart.sports_store.dto.PaymentDTO;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.vo.PaymentInfoVO;

public interface PaymentService {
    StatusCode addPaymentInfo(PaymentDTO paymentDTO);
    StatusCode deletePaymentInfo(Integer paymentId);
    PaymentDTO choosePaymentInfo(Integer paymentId);
    StatusCode updatePaymentInfo(Integer paymentId, PaymentDTO paymentDTO);
    PaymentInfoVO showPaymentInfo();
}
