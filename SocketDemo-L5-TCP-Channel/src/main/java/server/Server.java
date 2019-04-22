package server;

import constants.TCPConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author kangyuanjing
 * @version 1.0
 * @date 2019-04-17 21:10
 */
public class Server {

    public static void main(String[] args) throws IOException {
        TCPServer tcpServer = new TCPServer(TCPConstants.POST_SERVER);
        boolean isSucceed = tcpServer.start();
        if(!isSucceed){
            System.out.println("start TCP server failed");
            return;
        }

        UDPProvider.start(TCPConstants.POST_SERVER);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        do{
            str = reader.readLine();
            tcpServer.broadcast(str);
        }while (!"00bye00".equalsIgnoreCase(str));

        UDPProvider.stop();
        tcpServer.stop();
    }
}
