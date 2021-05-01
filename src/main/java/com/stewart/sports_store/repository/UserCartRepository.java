package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserCartRepository extends JpaRepository<UserCart, Integer> {
    UserCart findByUserIdAndItemId(Integer userId, Integer itemId);
    UserCart findByCartId(Integer cartId);
    List<UserCart> findByUserIdAndIsPaid(Integer userId, Boolean isPaid);
    void deleteByCartId(Integer cartId);
    void deleteAllByUserId(Integer userId);
}
