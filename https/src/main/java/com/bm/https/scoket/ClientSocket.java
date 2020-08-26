package com.bm.https.scoket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by 86133 on 2020/5/15.
 */
public class ClientSocket implements Runnable {


    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String key;
    private String message;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        //每5秒进行一次客户端连接，判断是否需要释放资源
        while (true){
            try {
                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (SocketHandler.isSocketClosed(this)){
                System.out.println("客户端已关闭,其Key值为："+ this.getKey());
                //关闭对应的服务端资源
                SocketHandler.close(this);
                break;
            }
        }
    }


}
