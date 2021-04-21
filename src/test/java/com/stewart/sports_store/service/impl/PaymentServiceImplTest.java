package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Test
    public void test() {
        System.out.println(paymentService.showPaymentInfo());
    }

}