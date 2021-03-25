package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {
}
