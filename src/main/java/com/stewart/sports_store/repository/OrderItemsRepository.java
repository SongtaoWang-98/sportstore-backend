package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    List<OrderItems> getByOrderByItemsIdDesc();
    List<OrderItems> findByItemsId(Integer itemsId);
}
