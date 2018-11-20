package com.spring.service.impl;

import com.spring.dao.UserDao;
import com.spring.entity.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User user) {
        userDao.save(user);
    }

    public List<User> getUser() {
        return userDao.findAll();
    }

    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }

    public User getById(Integer id) {
        return userDao.getOne(id);
    }
}
