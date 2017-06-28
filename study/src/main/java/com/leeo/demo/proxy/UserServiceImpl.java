package com.leeo.demo.proxy;

/**
 * @author lijun
 * @since 2017/6/28 15:03
 */
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("added!!!");
    }

    @Override
    public String get() {
        return "this is the user's name";
    }
}
