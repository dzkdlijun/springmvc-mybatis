package com.leeo.demo.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 字符输出流
 * Created by lijun on 2016/11/8.
 */
public class OutputStreamWriterDemo {
    public static void main(String[] args) throws IOException {
        writeFile("D:"+File.separator+"hello.txt");
    }

    public static void writeFile(String filePath) throws IOException {
        File file = new File(filePath);
        Writer writer = new FileWriter(file,true);
        writer.write("\r\n");//换行
        String str = "hello";
        writer.write(str);
        writer.close();
    }
}
