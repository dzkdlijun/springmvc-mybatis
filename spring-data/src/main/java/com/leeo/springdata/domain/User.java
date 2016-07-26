package com.leeo.springdata.domain;

/**
 * Created by lijun on 2016/7/26.
 */
public class User extends  BaseDomain {
    private String userName;
    private String passWord;

    public User() {
        super();
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}