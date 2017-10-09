package com.spring.controller;

import com.spring.entity.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    UserService userService;
    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }
    @RequestMapping("/getUser")
    @ResponseBody
    public List<User> getUser(){
        List<User> list = null;
        try {
            list = userService.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
}
