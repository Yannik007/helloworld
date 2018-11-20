package com.spring.controller;

import com.spring.elasticSearch.ElasticSearchClient;
import com.spring.entity.User;
import com.spring.service.UserService;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @RequestMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUser() {
        List<User> list = null;
        try {
            list = userService.getUser();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @RequestMapping(value = "/getUserById",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public User getUser(Integer id) {
        User user = null;
        try {
            user = userService.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return user;
    }

    @RequestMapping(value = "/insertUser", produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String insertUser(User user) {
        if(user.getName() == null || user.getName() == ""){
            return "用户名不能为空!";
        }
        if(user.getTrueName() == null || user.getTrueName() == ""){
            return "真实姓名不能为空!";
        }
        if(user.getCardId() == null || user.getCardId() == ""){
            return "身份证号码不能为空!";
        }
        if(user.getEmail() == null || user.getEmail() == ""){
            return "电子邮箱不能为空!";
        }
        if(user.getTelephone() == null || user.getTelephone() == ""){
            return "联系电话不能为空!";
        }
        if(user.getPwd() == null || user.getPwd() == ""){
            user.setPwd("123456");
        }
        user.setRegTime(new Date());
        user.setState(1);
        try {
            userService.insertUser(user);
        } catch (Exception e) {
        }
        return "添加成功！";
    }

    @RequestMapping(value = "/deleteUser", produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(String ids) {
        try {
            if (ids == null || ids == ""){
                return "参数不能为空！";
            }
            String[] id = ids.split(",");
            for (String myId:id) {
                Integer userId =Integer.parseInt(myId);
                userService.deleteUser(userId);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "删除成功！";
    }

    @RequestMapping(value = "/getClient", method = RequestMethod.GET)
    @ResponseBody
    public List getClient() {
        TransportClient client = elasticSearchClient.getClient();
        List<DiscoveryNode> nodes = client.connectedNodes();
        for (DiscoveryNode node : nodes) {
            System.out.println(node.getAddress());
        }
        return nodes;
    }

    @RequestMapping(value = "/addIndex", method = RequestMethod.POST)
    @ResponseBody
    public Object addIndex() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "tyw");
        json.put("postDate", new Date());
        json.put("message", "trying add elasticsearch");
        TransportClient client = elasticSearchClient.getClient();
        IndexResponse response = client.prepareIndex("apple", "phone", "1").setSource(json).get();
        return response;
    }

    @RequestMapping(value = "/updateIndex", method = RequestMethod.POST)
    @ResponseBody
    public Object updateIndex() {
        try {
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index("apple");
            updateRequest.type("phone");
            updateRequest.id("1");
            updateRequest.doc(XContentFactory.jsonBuilder()
                    .startObject()
                    .field("iphone", "iphone6")
                    .field("price", "4000")
                    .endObject());
            TransportClient client = elasticSearchClient.getClient();
            UpdateResponse response = client.update(updateRequest).get();
            return response;
        } catch (Exception e) {
            return "false";
        }
    }
    @RequestMapping(value = "/delIndex", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delIndex() {
        try {
            TransportClient client = elasticSearchClient.getClient();
            DeleteResponse response = client.prepareDelete("apple","phone","1").get();
            return response;
        } catch (Exception e) {
            return "false";
        }
    }
}
