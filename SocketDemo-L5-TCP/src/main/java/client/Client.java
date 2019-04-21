package client;

import client.bean.ServerInfo;

import java.io.IOException;

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
            try {
                TCPClient.linkWith(info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

