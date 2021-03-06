package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.service.HomeService;
import com.stewart.sports_store.vo.HomeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeServiceImplTest {
    @Autowired
    private HomeService homeService;

    @Test
    void findHomeVO() {
        HomeVO homeVO = homeService.findHomeVO();

    }
}