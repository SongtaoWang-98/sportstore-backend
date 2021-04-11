package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.dto.RegisterDTO;
import com.stewart.sports_store.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegisterServiceImplTest {
    @Autowired
    private RegisterService registerService;

    @Test
    void test() {
        RegisterDTO dto = new RegisterDTO(
                "1234",
                "4",
                "123"
        );
        registerService.register(dto);
    }
}