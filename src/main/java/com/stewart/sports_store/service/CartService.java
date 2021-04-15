package com.stewart.sports_store.service;

import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.vo.CartVO;

public interface CartService {
    StatusCode addItem(Integer itemId);
    StatusCode deleteItem(Integer cartId);
    StatusCode updateItem(Integer cartId, Integer num);
    CartVO viewCart();
}
