package com.leeo.demo.array;

/**
 * Created by DELL on 2016/11/20.
 */
public class ArrayTest {

    public static void main(String[] args) {
        int[] array = new int[2];
        array[0] = 2;
        array[1] = 4;

        int a = array[1];
        a ++;
        System.out.println(a);
        System.out.println(array[1]);

        String[] sArray = new String[]{"lijun","test"};
        String s = sArray[1];
        s = s+"test";
        System.out.println(s);
        System.out.println(sArray[1]);

        Person person1= new Person("lijun");
        person1.type = "person";
        Person person2 = new Person("leeo");
        System.out.println(Person.type);
        System.out.println(person2.type);
    }
}
