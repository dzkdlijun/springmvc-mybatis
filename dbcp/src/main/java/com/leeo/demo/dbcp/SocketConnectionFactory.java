package com.leeo.demo.dbcp;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lijun on 2016/9/29.
 */
public class SocketConnectionFactory extends BasePooledObjectFactory<Socket> {

    private static final Logger logger = Logger.getLogger(SocketConnectionFactory.class);

    private List<InetSocketAddress> socketAddresses = null;

    private final AtomicLong atomicLongCount;

    public SocketConnectionFactory(String hosts) {
        socketAddresses = new ArrayList<InetSocketAddress>();

        String[] hostArray = hosts.split(",");
        if (hostArray.length > 0) {
            for (String host : hostArray) {
                String[] dataString = host.split(":");
                InetSocketAddress address = new InetSocketAddress(dataString[0], Integer.parseInt(dataString[1]));
                socketAddresses.add(address);

            }
        }

        atomicLongCount = new AtomicLong();

    }

    public static void main(String[] args) {
        SocketConnectionFactory factory = new SocketConnectionFactory("localhost:8777");
        for (int i = 0;i<50;i++){
            factory.getSocketAddress();
        }
    }

    private InetSocketAddress getSocketAddress(){
        int index = (int) (atomicLongCount.getAndIncrement() % socketAddresses.size());
        logger.info("调用服务器地址："+socketAddresses.get(index).getHostName());
        return socketAddresses.get(index);
    }

    public void destroyObject(PooledObject<Socket> p) throws IOException {
        Socket socket = p.getObject();
        logger.info("销毁Socket:" + socket);
        if(socket!=null){
            socket.close();
        }
    }

    public boolean validateObject(PooledObject<Socket> p){
        Socket socket = p.getObject();
        if(socket!=null){
            if(!socket.isConnected()){
                return false;
            }
            if(socket.isClosed()){
                return false;
            }

            try {
                return socket.getKeepAlive();
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Socket create() throws Exception {
        Socket socket = new Socket();
        socket.connect(getSocketAddress());
        return socket;
    }

    public PooledObject<Socket> wrap(Socket obj) {
        return new DefaultPooledObject<Socket>(obj);
    }
}
