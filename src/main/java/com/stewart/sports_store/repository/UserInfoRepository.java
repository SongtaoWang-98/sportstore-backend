package com.stewart.sports_store.repository;

import com.stewart.sports_store.entity.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserInfoRepository extends JpaRepository <UserInfo, Integer> {
    UserInfo findByUserName(String userName);
}
