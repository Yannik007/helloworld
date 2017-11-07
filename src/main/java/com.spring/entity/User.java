package com.spring.entity;

import java.util.Date;

public class User {
    /*
    *主键
    * */
    private Integer id;
    /*
    * 用户名*/
    private String userName;
    /*用户密码*/
    private String password;
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private  Date updateTime;
    /*状态*/
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{}";
    }
}
