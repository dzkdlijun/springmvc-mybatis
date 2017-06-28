package com.leeo.demo.proxy;

/**
 * @author lijun
 * @since 2017/6/28 15:03
 */
public class ProxyTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
        UserService proxy = (UserService) invocationHandler.getProxy();
        proxy.add();
        System.out.println(proxy.get());
    }
}
