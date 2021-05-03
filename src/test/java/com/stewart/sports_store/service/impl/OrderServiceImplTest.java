package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.dto.OrderDTO;
import com.stewart.sports_store.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void test() {
//        private Integer[] cartIds;
//        private Integer paymentId;
//        private String deliveryType;
//        private String paymentMethod;
        OrderDTO orderDTO = new OrderDTO(
                new Integer[]{17,19},
                14, "普通达", "支付宝"
        );
        System.out.println(orderService.createOrder(orderDTO));
    }
}