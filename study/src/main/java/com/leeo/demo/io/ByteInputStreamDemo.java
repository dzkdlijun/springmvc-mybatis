package com.leeo.demo.io;

import java.io.*;

/**
 * 字节输入流
 * Created by lijun on 2016/11/7.
 */
public class ByteInputStreamDemo {
    public static void main(String[] args) throws IOException {
//        readFileUknownSize("D:"+File.separator+"test.txt");
    readByDataInputStream("D:"+File.separator+"data.txt");
    }

    /**
     * 一次性读取整个文件
     *
     * @param filePath
     * @throws IOException
     */
    public static void read1(String filePath) throws IOException {
        File f = new File(filePath);
        InputStream in = new FileInputStream(f);
        byte[] result = new byte[(int) f.length()];//根据文件大小动态分配数组大小
        int length = in.read(result);
        //逐字节读取，节省空间
//        for(int i=0;i<result.length;i++){
//            result[i] = (byte) in.read();
//        }
        //end of 逐字节读取
        in.close();
        System.out.println(new String(result, 1, length));//去掉转换成字符串后的空格
    }

    /**
     * 读取未知大小文件，用结束符判断是否已读取完
     *
     * @param filePath
     * @throws IOException
     */
    public static void readFileUknownSize(String filePath) throws IOException {
        File f = new File(filePath);
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[1024];
        int count = 0;
        int temp = 0;
        while ((temp = in.read()) != -1) {
            b[count++] = (byte) temp;
        }
        in.close();
        System.out.println(new String(b, 0, count));
    }

    /**
     * 数据流读取
     * @param filePath
     * @throws IOException
     */
    public static void readByDataInputStream(String filePath) throws IOException {
        File f = new File(filePath);
        DataInputStream in = new DataInputStream(new FileInputStream(f));
        char[] ch = new char[1024];
        int count = 0;
        char temp;
        try{
            //这里要求写入方式必须跟读取方式一致，否则会出现乱码
            while ((temp = in.readChar()) != 'C') {
                ch[count++] = temp;
            }
        }catch (EOFException e){
            e.printStackTrace();
        }

        System.out.println(ch);
    }

    public static void pushBackInputStreamDemo() throws IOException {
        String str = "hello,world!";
        PushbackInputStream push = null;
        ByteArrayInputStream bat = null;
        bat = new ByteArrayInputStream(str.getBytes());
        push = new PushbackInputStream(bat);
        int temp = 0;
        while ((temp = push.read())!=-1){
            if(temp == ','){
                push.unread(temp);//先回退，再读取
                temp = push.read();
                System.out.println("回退" + (char)temp);
            }else {
                System.out.println((char)temp);
            }
        }
    }
}
