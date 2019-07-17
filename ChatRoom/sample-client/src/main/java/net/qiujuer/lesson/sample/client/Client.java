package net.qiujuer.lesson.sample.client;

import net.qiujuer.lesson.sample.client.bean.ServerInfo;

import java.io.*;
import java.net.Socket;

/**
 * @author kangyuanjing
 * @version 1.0
 * @date 2019-04-18 21:44
 */
public class Client {
    public static void main(String[] args) {
        ServerInfo info = UDPSearcher.searchServer(10000);
        System.out.println("Server:" + info);

        if(info != null){
            TCPClient tcpClient = null;
            try {
                tcpClient = TCPClient.startWith(info);
                if(tcpClient == null){
                    return;
                }
                write(tcpClient);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                tcpClient.exit();
            }
        }
    }

    private static void write(TCPClient tcpClient) throws IOException {
        // 构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        do {
            String str = input.readLine();
            // 发送
            tcpClient.send(str);
            if ("00bye00".equalsIgnoreCase(str)) {
                break;
            }
        } while (true);
    }

}

