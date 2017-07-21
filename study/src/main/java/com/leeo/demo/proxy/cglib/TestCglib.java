package com.leeo.demo.proxy.cglib;

/**
 * @author lijun
 * @since 2017/6/29 15:51
 */
public class TestCglib {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        UserServiceImpl userService = (UserServiceImpl) cglibProxy.getInstance(new UserServiceImpl());
        System.out.println(userService.getName());
    }
}
