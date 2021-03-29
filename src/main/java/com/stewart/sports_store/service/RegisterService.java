package com.stewart.sports_store.service;

import com.stewart.sports_store.dto.RegisterDTO;
import com.stewart.sports_store.enums.RegisterCode;

public interface RegisterService {
    RegisterCode register(RegisterDTO registerDTO);
}
