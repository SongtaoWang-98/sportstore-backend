package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Integer> {
    PaymentInfo findByPaymentId(Integer paymentId);
    List<PaymentInfo> findByUserId(Integer userId);
}
