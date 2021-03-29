package com.stewart.sports_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String userName;
    private String userTel;
    private String passcode;
    private String confirm;
}
