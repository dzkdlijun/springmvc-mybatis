package com.leeo.demo.io;

import java.io.*;

/**
 * 自己输出流
 * Created by lijun on 2016/11/7.
 */
public class ByteOutputStreamDemo {

    public static void main(String[] args) throws IOException {
//        writeString("D:"+File.separator+"hello.txt");
//        writeByte("D:"+File.separator+"hello.txt");
//        copyFile("D:"+File.separator+"hello.txt","D:"+File.separator+"hello2.txt");
        writeByDataOutputStream("D:"+File.separator+"data.txt");
    }

    /**
     * 输出重定向至文件
     * @param filePath
     */
    public static void printRedirect(String filePath){
        System.out.println("hello");
        File file = new File(filePath);
        try{
            System.setOut(new PrintStream(new FileOutputStream(file)));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("这些内容在文件中才能看到哦！");
    }

    /**
     * 写入字符串
     * @param filePath
     * @throws IOException
     */
    public static void writeString(String filePath) throws IOException {
        String str = "hello,world!";
        File f = new File(filePath);
        OutputStream out = new FileOutputStream(f);
        out.write(str.getBytes());
        out.close();
    }

    /**
     * 写入字节
     * @param filePath
     * @throws IOException
     */
    public static void writeByte(String filePath) throws IOException {
        File f = new File(filePath);
        OutputStream out = new FileOutputStream(f,true);//直接追加
        String str = "hello,world!";
        byte[] b = str.getBytes();
        for(byte b1:b){
            out.write(b1);
        }
        out.close();
    }

    /**
     * 复制文件
     * @param srcFilePath
     * @param desFilePath
     * @throws IOException
     */
    public static void copyFile(String srcFilePath,String desFilePath) throws IOException {
        File srcFile = new File(srcFilePath);
        File desFile = new File(desFilePath);

        if(!srcFile.exists()){
            System.out.println("文件不存在");
            System.exit(1);
        }

        InputStream in = new FileInputStream(srcFile);
        OutputStream out = new FileOutputStream(desFile);
        if(in!=null&&out!=null){
            int temp = 0;
            while((temp=in.read())!=-1){
                out.write(temp);
            }
        }

        in.close();
        out.close();
    }

    /**
     * 使用内存操作流转换字母大小写
     */
    public static void toLowerCase() throws IOException {
        String str = "OSUDFLSLD";
        ByteArrayInputStream input = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int temp = 0;
        while ((temp=input.read())!=-1){
            char ch = (char) temp;
            output.write(Character.toLowerCase(ch));
        }
        System.out.println(output.toString());
        input.close();
        output.close();
    }

    /**
     * 数据流写入数据
     * @param filePath
     * @throws IOException
     */
    public static void writeByDataOutputStream(String filePath) throws IOException {
        File f = new File(filePath);
        char[] ch = {'g','你','节'};
        DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
        for(char temp:ch){
            out.writeChar(temp);
        }
        out.close();
    }
}
