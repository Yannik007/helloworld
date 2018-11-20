package com.spring.dao;

import com.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends JpaRepository<User,Integer>, CrudRepository<User,Integer> {
}
