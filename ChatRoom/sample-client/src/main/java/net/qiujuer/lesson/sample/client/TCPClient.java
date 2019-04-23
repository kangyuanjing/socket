package net.qiujuer.lesson.sample.client;

import net.qiujuer.lesson.sample.client.bean.ServerInfo;
import net.qiujuer.library.clink.utils.CloseUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

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

        try {
            ReadHandler readHandler = new ReadHandler(socket.getInputStream());
            readHandler.start();
            write(socket);
            readHandler.exit();
        } catch (IOException e) {
            System.out.println("异常关闭");
        }

        socket.close();
        System.out.println("客户端已退出");

    }

    private static void write(Socket socket) throws IOException {
        // 构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        // 得到输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);

        do {
            String str = input.readLine();
            // 发送
            socketPrintStream.println(str);

            if ("00bye00".equalsIgnoreCase(str)) {
                break;
            }
        } while (true);

        socketPrintStream.close();
    }

    static class ReadHandler extends Thread {

        private boolean done = false;
        private final InputStream inputStream;

        public ReadHandler(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            super.run();

            try {
                // 得到输入流 用于接收数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(inputStream));
                do {
                    String str = null;
                    try {
                        str = socketInput.readLine();
                    } catch (SocketTimeoutException e) {
                        continue;
                    }
                    if (str == null) {
                        System.out.println("连接已关闭，无法读取数据");
                        break;
                    }
                    System.out.println(str);
                } while (!done);

            } catch (IOException e) {
                if (!done) {
                    System.out.println("连接异常断开" + e.getMessage());
                }
            } finally {
                CloseUtils.close(inputStream);
            }

        }

        void exit() {
            done = true;
            CloseUtils.close(inputStream);
        }
    }

}
