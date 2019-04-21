package server;

import constants.TCPConstants;

import java.io.IOException;

/**
 * @author kangyuanjing
 * @version 1.0
 * @date 2019-04-17 21:10
 */
public class Server {

    public static void main(String[] args) {
        TCPServer tcpServer = new TCPServer(TCPConstants.POST_SERVER);
        boolean isSucceed = tcpServer.start();
        if(!isSucceed){
            System.out.println("start TCP server failed");
            return;
        }

        UDPProvider.start(TCPConstants.POST_SERVER);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UDPProvider.stop();
        tcpServer.stop();
    }
}
