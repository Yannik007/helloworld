package com.spring.service;

import com.spring.entity.User;

import java.util.List;

/**
 *用户接口层
 */
public interface UserService {
    List<User> getUser();
    void insertUser(User user);
    void deleteUser(Integer id);
    User getById(Integer id);
}
