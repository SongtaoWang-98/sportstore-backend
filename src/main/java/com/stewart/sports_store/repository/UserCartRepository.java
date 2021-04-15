package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCartRepository extends JpaRepository<UserCart, Integer> {
    UserCart findByUserIdAndItemId(Integer userId, Integer itemId);
    UserCart findByCartId(Integer cartId);
    List<UserCart> findByUserId(Integer userId);

}
