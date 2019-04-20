package client;

import client.bean.ServerInfo;

/**
 * @author kangyuanjing
 * @version 1.0
 * @date 2019-04-18 21:44
 */
public class Client {
    public static void main(String[] args) {
        ServerInfo info = ClientSearcher.searchServer(10000);
        System.out.println("Server:" + info);
    }
}

