package com.bm.https.scoket;

import com.bm.https.center.RecordController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by 86133 on 2020/5/15.
 */
public class ChatClient {
    private static Logger log = LoggerFactory.getLogger(RecordController.class);
    public static void main(String[] args) throws IOException {
        String host = "59.207.219.23";
        int port = 8001;

        //与服务端建立连接
        Socket socket = new Socket(host, port);
        socket.setOOBInline(true);

        //建立连接后获取输出流
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        OutputStreamWriter opsw = new OutputStreamWriter(outputStream);
        BufferedWriter bw = new BufferedWriter(opsw);
        bw.write("hello world");
        bw.flush();
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        String uuid = UUID.randomUUID().toString();

        outputStream.write(uuid.getBytes());
        log.info("uuid: {}", uuid);
        byte[] bytes = new byte[0];
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String cc = new String(bytes);
        log.info("====="+cc);
    }
}
