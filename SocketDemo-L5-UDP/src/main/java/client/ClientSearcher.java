package client;

import client.bean.ServerInfo;
import constants.UDPConstants;

import java.util.concurrent.CountDownLatch;

/**
 * @author kangyuanjing
 * @version 1.0
 * @date 2019-04-18 21:45
 */
public class ClientSearcher {
    private static final int LISTEN_PORT = UDPConstants.PORT_CLIENT_RESPONSE;

    public static ServerInfo searchServer(int timeout) {
        System.out.println("UDPSearcher Started.");
        CountDownLatch receiveLatch = new CountDownLatch(1)
    }
}
