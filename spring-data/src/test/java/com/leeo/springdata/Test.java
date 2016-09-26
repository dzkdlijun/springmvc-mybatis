package com.leeo.springdata;

/**
 * Created by DELL on 2016/9/12.
 */
public class Test {

    private  int x;

    private String s;

    public Test(int x) {
        this.x = x;
    }

    public Test(String s) {
        this.s = s;
    }

    public Test(int x,String s){
        this(x);
        this.s =s ;
    }
}
