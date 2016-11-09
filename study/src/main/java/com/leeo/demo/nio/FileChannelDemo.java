package com.leeo.demo.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * Created by lijun on 2016/11/9.
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
    randomAccessFile("D:"+ File.separator+"hello.txt");
    }

    public static void randomAccessFile(String filePath) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile(filePath,"rw");
        FileChannel inChannel = accessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(24);

        int bytesRead = inChannel.read(buffer);

        while (bytesRead!=-1){
            System.out.println("Read:"+bytesRead);
            buffer.flip();//切换到读模式

            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }

            /**
             * 读取完后，情况缓冲区后可以再次写入，clear()方法会清空整个缓冲区;
             * compact()方法只会清除已经读过的数据,并且将未读数据放在缓冲区前端，新读取入数据自动拼接到后面
             */
            buffer.clear();
//            buffer.compact();
            bytesRead = inChannel.read(buffer);
        }

        accessFile.close();

    }

    public static void selectorDemo(String filePath) throws IOException {
        Selector selector = Selector.open();
        RandomAccessFile accessFile = new RandomAccessFile(filePath,"rw");
        FileChannel fileChannel =  accessFile.getChannel();
//        fileChannel.
    }
}
