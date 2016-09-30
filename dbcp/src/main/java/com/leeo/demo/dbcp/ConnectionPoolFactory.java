package com.leeo.demo.dbcp;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.net.Socket;

/**
 * Created by lijun on 2016/9/29.
 */
public class ConnectionPoolFactory {
    private GenericObjectPool<Socket> pool = null;

    private static ConnectionPoolFactory instance = null;

    private static String hosts = "localhost:8080";

    public static ConnectionPoolFactory getInstance(){
        if(instance == null){
            synchronized (ConnectionPoolFactory.class){
                if(instance == null) {
                    GenericObjectPoolConfig config = new GenericObjectPoolConfig();
                    config.setMaxIdle(16);
                    config.setMaxWaitMillis(3000l);
                    config.setMinEvictableIdleTimeMillis(3000l);
                    config.setTestOnBorrow(true);
                    config.setTestOnCreate(true);
                    config.setTestOnReturn(true);
                    config.setMaxTotal(20);
                    instance = new ConnectionPoolFactory(config,hosts);
                }
            }
        }
        return instance;
    }

    private ConnectionPoolFactory(GenericObjectPoolConfig config,String hosts){
        SocketConnectionFactory factory = new SocketConnectionFactory(hosts);
        pool = new GenericObjectPool<Socket>(factory,config);
    }

    public Socket getConnection() throws Exception {
        return pool.borrowObject();
    }

    public void releaseConnection(Socket socket){
        pool.returnObject(socket);
    }
}
