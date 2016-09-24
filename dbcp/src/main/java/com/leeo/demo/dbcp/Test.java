package com.leeo.demo.dbcp;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.net.Socket;

/**
 * Created by lijun on 2016/9/24.
 */
public class Test {
    public static void main(String[] args) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(16);
        config.setMaxWaitMillis(30000);
        SocketPoolFactory poolFactory = new SocketPoolFactory(config,"localhost",8777);
        Socket socket = null;
        try {
            socket = poolFactory.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(socket!=null){
                poolFactory.releaseConnection(socket);
            }
        }
    }
}
