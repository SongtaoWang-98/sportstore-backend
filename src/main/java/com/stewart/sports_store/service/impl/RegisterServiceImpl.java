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
        UserInfo u = userInfoRepository.findByUserName(registerDTO.getUserName());
        if(u != null) return RegisterCode.USERNAME_EXISTS;

        UserInfo ut = userInfoRepository.findByUserTel(registerDTO.getUserTel());
        if(ut != null) return RegisterCode.TEL_EXISTS;

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(registerDTO.getUserName());
        userInfo.setUserTel(registerDTO.getUserTel());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userInfo.setUserPassword(passwordEncoder.encode(registerDTO.getPasscode()));
        userInfoRepository.save(userInfo);
        return RegisterCode.SUCCESS;

    }
}
