package com.stewart.sports_store.security;

import com.stewart.sports_store.entity.UserInfo;
import com.stewart.sports_store.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUserName(s);
        if(userInfo == null) {
            throw new InternalAuthenticationServiceException("用户不存在！");
        }
        return new User(userInfo.getUserName(), userInfo.getUserPassword(),
                true, true, true, true,
                new ArrayList<GrantedAuthority>(Collections.singleton(new SimpleGrantedAuthority("BestBuyer"))));
    }
}
