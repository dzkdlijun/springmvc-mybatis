package com.leeo.demo.nio.timeserver;/**
 * Created by lijun on 2017/2/21.
 */

/**
 * TimeClient
 *
 * @author lijun
 * @since 2017/2/21
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8994;
        if(args!=null&&args.length>0){
            try{
               port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }

        new Thread(new TimeClientHandle(null,port),"client-01").start();
    }
}
