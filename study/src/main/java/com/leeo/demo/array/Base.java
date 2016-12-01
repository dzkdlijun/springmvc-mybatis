package com.leeo.demo.array;

/**
 * Created by DELL on 2016/11/20.
 */
public class Base {
    private String desc;
    public int count =2;

    public Base(String desc) {
        this.desc = desc;
    }

    public void print(){
        System.out.println(count);
    }

    public Base() {
        this.desc = getDesc();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toString(){
        return desc;
    }
}
