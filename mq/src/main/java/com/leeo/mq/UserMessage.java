package com.leeo.mq;

/**
 * @author lijun
 * @since 2017/7/25 18:12
 */
public class UserMessage {
    private String name;
    private int age;

    public UserMessage() {
    }

    public UserMessage(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
