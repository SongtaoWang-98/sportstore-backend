package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.dto.RegisterDTO;
import com.stewart.sports_store.entity.UserInfo;
import com.stewart.sports_store.enums.RegisterCode;
import com.stewart.sports_store.repository.UserInfoRepository;
import com.stewart.sports_store.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public RegisterCode register(RegisterDTO registerDTO) {
        if(registerDTO.getUserName() == null) return RegisterCode.USERNAME_NULL;
        else if (registerDTO.getUserTel() == null) return RegisterCode.TEL_NULL;
        else if (!registerDTO.getPasscode().equals(registerDTO.getConfirm())) return RegisterCode.PASSWORD_DIFFERENT;
        else{
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(registerDTO.getUserName());
            userInfo.setUserTel(registerDTO.getUserTel());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userInfo.setUserPassword(passwordEncoder.encode(registerDTO.getPasscode()));
            userInfoRepository.save(userInfo);
            return RegisterCode.SUCCESS;
        }
    }
}
