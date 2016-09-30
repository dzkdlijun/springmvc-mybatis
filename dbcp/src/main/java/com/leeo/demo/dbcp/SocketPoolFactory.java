package com.leeo.demo.dbcp;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by lijun on 2016/9/24.
 */
public class SocketPoolFactory {
    private GenericObjectPool<Socket> pool;

    public SocketPoolFactory(GenericObjectPoolConfig config, String ip, int port) {
        ConnectionFactory factory = new ConnectionFactory(ip,port);
        pool = new GenericObjectPool<Socket>(factory,config);
    }

    public Socket getConnection() throws Exception {
        return pool.borrowObject();
    }

    public void releaseConnection(Socket socket){
        try{
            pool.returnObject(socket);
        }catch (Exception e){
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    class ConnectionFactory extends BasePooledObjectFactory{

        private InetSocketAddress address;

        public ConnectionFactory(String ip,int port) {
            this.address = new InetSocketAddress(ip,port);
        }

        public Object create() throws Exception {
            Socket socket = new Socket();
            socket.connect(address);
            return socket;
        }

        public PooledObject wrap(Object obj) {
            return new DefaultPooledObject(obj);
        }

        public void destroyObject(Object obj)
                throws Exception  {
            if(obj instanceof Socket){
                ((Socket) obj).close();
            }
        }

        public boolean validateObject(Object obj){
            if(obj instanceof Socket){
                Socket socket = (Socket)obj;
                if(!socket.isConnected()){
                    return false;
                }
                if(socket.isClosed()){
                    return false;
                }
                return true;
            }

            return false;
        }
    }
}
