package com.leeo.demo.array;

/**
 * Created by DELL on 2016/11/20.
 */
public class Parent extends Base {
    private String name;

    public Parent( String name) {
        this.name = name;
    }

    public String getDesc(){
        return "name:"+this.name;
    }

    public int count = 20;

    public void print(){
        System.out.println(count);
    }


    public Parent() {
    }

    public static void main(String[] args) {
        System.out.println(new Parent("lijun"));

        Base base = new Parent();
        System.out.println(base.count);
        base.print();
    }
}
