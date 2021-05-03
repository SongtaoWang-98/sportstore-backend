package com.stewart.sports_store.controller;

import com.stewart.sports_store.dto.RegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegisterControllerTest {

    @Autowired
    RegisterController registerController;

    @Test
    void test() {
        RegisterDTO dto = new RegisterDTO(
                "123455533",
                "444444333",
                "123"
        );
        System.out.println(registerController.register(dto));
    }

}