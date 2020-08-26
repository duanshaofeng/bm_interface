package com.bm.https.scoket;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.center.RecordController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by 86133 on 2020/5/15.
 */
@Component
public class SocketController {
    private Logger log = LoggerFactory.getLogger(RecordController.class);
    //@Value("${socket.port}")
    private Integer port = 8001;
    private boolean started;
    private ServerSocket serverSocket;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args){
        //new SocketServer().start(8068);
    }

    public void start(){
        start(null);
    }
   /* @Autowired
    private UserService userService;//测试使用*/


    public void start(Integer port){
        log.info("port: {}, {}", this.port, port);
        try {
            serverSocket =  new ServerSocket(port == null ? this.port : port);
            started = true;
            log.info("Socket服务已启动，占用端口： {}", serverSocket.getLocalPort());
        } catch (IOException e) {
            log.error("端口冲突,异常信息：{}", e);
            System.exit(0);
        }

        while (started){
            try {
                Socket socket = serverSocket.accept();
                socket.setKeepAlive(true);
                ClientSocket register = SocketHandler.register(socket);
                String message = register.getMessage();

               /* StringBuilder content = new StringBuilder();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = br.readLine()) != null) {
                    content.append(line);
                }*/

                log.info("客户端已连接，其Key值为：{}",message);
                log.info("客户端已连接，其message值为：{}", message);
                List<String> list = new ArrayList<>();
                list.add("1");
                list.add("1");
                list.add("1");
                list.add("1");

                SocketHandler.sendMessage(register, JSONObject.toJSONString(list));
                if (register != null){
                    executorService.submit(register);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
