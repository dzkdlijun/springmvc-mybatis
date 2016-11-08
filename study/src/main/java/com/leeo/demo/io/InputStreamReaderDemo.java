package com.leeo.demo.io;

import java.io.*;
import java.util.Scanner;

/**
 * 字符流输入流
 * Created by lijun on 2016/11/8.
 */
public class InputStreamReaderDemo {
    public static void main(String[] args) throws IOException {
//        readFile("D:"+File.separator+"hello.txt");
//        bufferReadSystemIn();
        System.out.println(File.separator+"  "+File.pathSeparator);
        System.out.println("系统默认编码为："+ System.getProperty("file.encoding"));
//        readFileByScanner("D:"+File.separator+"hello.txt");
    }

    /**
     * 循环读取
     * @param filePath
     * @throws IOException
     */
    public static void readFile(String filePath) throws IOException {
        File f = new File(filePath);
        char [] ch = new char[100];
        Reader reader = new FileReader(f);
        int temp = 0;
        int count = 0;
        while ((temp= reader.read())!=-1){
            ch[count++] = (char) temp;
        }
        reader.close();
        System.out.println(new String(ch,0,count));
    }

    public static void bufferReadSystemIn(){
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("请输入内容");
        try{
            str = buf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("你输入的内容时："+str);
    }

    public static void readFileByScanner(String filePath){
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String str = scanner.next();
        scanner.close();
        System.out.println(str);
    }

}
