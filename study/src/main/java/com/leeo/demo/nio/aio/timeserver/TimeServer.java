package com.leeo.demo.nio.aio.timeserver;/**
 * Created by lijun on 2017/2/21.
 */

/**
 * @author lijun
 * @since 2017/2/21
 */
public class TimeServer {
    public static void main(String[] args) {
      new Thread(new AsyncTimeServerHandler(8994),"aio-server-01").start();
    }
}
