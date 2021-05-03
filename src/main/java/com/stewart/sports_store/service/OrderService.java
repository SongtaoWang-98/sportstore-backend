package com.stewart.sports_store.service;

import com.stewart.sports_store.dto.OrderDTO;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.vo.OrderInfoVO;
import com.stewart.sports_store.vo.OrderSuccessVO;

public interface OrderService {
    OrderSuccessVO createOrder(OrderDTO orderDTO);
    StatusCode deleteOrder(Integer orderId);
    OrderInfoVO viewOrder();
}
