package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Integer> {
}
