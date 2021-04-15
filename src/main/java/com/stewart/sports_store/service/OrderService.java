package com.stewart.sports_store.service;

import com.stewart.sports_store.dto.OrderDTO;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.vo.OrderInfoVO;

public interface OrderService {
    StatusCode createOrder(OrderDTO orderDTO);
    StatusCode deleteOrder(Integer orderId);
    OrderInfoVO viewOrder();
}
