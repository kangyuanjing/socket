package net.qiujuer.lesson.sample.foo.constants;

/**
 * @author kangyuanjing
 * @version 1.0
 * @date 2019-04-22 20:43
 */
public class UDPConstants {
    // 公用头部
    public static byte[] HEADER = new byte[]{7, 7, 7, 7, 7, 7, 7, 7};
    // 服务器固化UDP接收端口
    public static int PORT_SERVER = 30201;
    // 客户端回送端口
    public static int PORT_CLIENT_RESPONSE = 30202;
}
