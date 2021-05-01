package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {
    List<OrderInfo> findByUserId(Integer userId);
    OrderInfo findByItemsId(Integer itemsId);
}
