package com.leeo.demo.nio.aio.timeserver;/**
 * Created by lijun on 2017/2/21.
 */

/**
 * @author lijun
 * @since 2017/2/21
 */
public class TimeClient {
    public static void main(String[] args) {
        new Thread(new AsyncTimeClientHandler("127.0.0.1",8994),"aio-client-01").start();
    }
}
