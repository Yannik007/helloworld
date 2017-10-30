package com.spring.controller;

import com.spring.elasticSearch.ElasticSearchClient;
import com.spring.entity.User;
import com.spring.service.UserService;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

    private static final Logger logger= LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ElasticSearchClient elasticSearchClient;
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
            logger.error(e.getMessage(), e);
        }
        return  list;
    }
    @RequestMapping("/getClient")
    public  String getClient(){
        TransportClient client = elasticSearchClient.getClient();
        client.prepareGet();
        return "hello";
    }
}
