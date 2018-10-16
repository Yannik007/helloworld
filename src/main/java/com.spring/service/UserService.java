package com.spring.service;

import com.spring.entity.User;

import java.util.List;

/*
 *用户接口层 */
public interface UserService {
    List<User> getUser()throws Exception;
    User insertUser(User user)throws Exception;
}
