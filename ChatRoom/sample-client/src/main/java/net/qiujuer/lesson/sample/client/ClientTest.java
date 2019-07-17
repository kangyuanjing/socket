package net.qiujuer.lesson.sample.client;

import net.qiujuer.lesson.sample.client.bean.ServerInfo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author kangyuanjing
 * @version 1.0
 * @date 2019-05-04 15:59
 */
public class ClientTest {
    private static boolean done;
    private static Thread thread;

    public static void main(String[] args) throws IOException {
        ServerInfo info = UDPSearcher.searchServer(10000);
        System.out.println("Server:" + info);
        if (info == null) {
            return;
        }
        int size = 0;
        final ArrayList<TCPClient> tcpClients = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                TCPClient tcpClient = TCPClient.startWith(info);
                if(tcpClient == null){
                    System.out.println("连接异常");
                    continue;
                }
                tcpClients.add(tcpClient);
                System.out.println("连接成功：" + (++size));
            } catch (IOException e) {
                System.out.println("连接异常");
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.in.read();
        thread = new Thread(() -> {
            while (!done){
                for (TCPClient tcpClient : tcpClients) {
                    tcpClient.send("hello");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.in.read();
        System.out.println("2");
        done = true;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (TCPClient tcpClient : tcpClients) {
            tcpClient.exit();
        }
    }
}
