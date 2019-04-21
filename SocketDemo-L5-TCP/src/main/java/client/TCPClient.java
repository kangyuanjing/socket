package client;

import client.bean.ServerInfo;

import java.io.*;
import java.net.*;

/**
 * @author kangyuanjing
 * @version 1.0
 * @date 2019-04-21 20:08
 */
public class TCPClient {
    public static void linkWith(ServerInfo info) throws IOException {
        Socket socket = new Socket();
        socket.setSoTimeout(3000);

        socket.connect(new InetSocketAddress(InetAddress.getByName(info.getAddress()), info.getPort()));
        System.out.println("已发起服务器连接，并进入后续流程");
        System.out.println("客户端信息：" + socket.getLocalAddress() + "\tP:" + socket.getLocalPort());
        System.out.println("服务器信息：" + socket.getInetAddress() + "\tP:" + socket.getPort());

        todo(socket);

        socket.close();
        System.out.println("客户端已退出");

    }

    private static void todo(Socket socket) throws IOException {
        // 构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        // 得到输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);

        // 得到socket输入流
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        boolean flag = true;
        do{
            String str = input.readLine();
            // 发送
            socketPrintStream.println(str);

            // 从服务器读取
            String echo = socketBufferedReader.readLine();
            if("bye".equalsIgnoreCase(echo)){
                flag = false;
            }else {
                System.out.println(echo);
            }
        }while (flag);

        input.close();
        socketPrintStream.close();
        socketBufferedReader.close();
    }


}
