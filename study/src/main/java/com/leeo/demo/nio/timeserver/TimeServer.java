package com.leeo.demo.nio.timeserver;/**
 * Created by lijun on 2017/2/21.
 */

/**
 * 时间服务器
 *
 * @author lijun
 * @since 2017/2/21
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8994;
        if (args != null && args.length > 0) {
            try{
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                System.out.println();
            }
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"server-0").start();
    }
}
