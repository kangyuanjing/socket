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
        ServerProvider.start(TCPConstants.POST_SERVER);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerProvider.stop();
    }
}
