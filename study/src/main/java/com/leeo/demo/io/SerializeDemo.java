package com.leeo.demo.io;

import java.beans.Transient;
import java.io.*;

/**
 * Created by lijun on 2016/11/8.
 */
public class SerializeDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ser("D:"+File.separator+"sertest.txt");
        dser("D:"+File.separator+"sertest.txt");
    }

    public static void ser(String filePath) throws IOException {
        File file = new File(filePath);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(new Person("lijun",24));
        out.close();
    }

    public static void dser(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Person person = (Person) in.readObject();
        in.close();
        System.out.println(person);
    }

    public static class Person implements Serializable{
        private String name;
        private transient int age;//transient 关键字表示不序列化该字段

        public Person(String name, int age) {
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

        public String toString(){
            return String.format("姓名:"+name+" 年龄:"+age);
        }
    }
}
