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

    @RequestMapping("/getUser")
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

    @RequestMapping("/insertUser")
    @ResponseBody
    public User insertUser() {
        User  result = null;
        User user = new User();
        user.setCreateTime(new Date());
        user.setUserName("admin");
        user.setPassword("admin");
        user.setState(1);
        try {
            result= userService.insertUser(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
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
