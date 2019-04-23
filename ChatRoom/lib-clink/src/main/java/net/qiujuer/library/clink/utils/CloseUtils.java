package net.qiujuer.library.clink.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author kangyuanjing
 * @version 1.0
 * @date 2019-04-22 14:02
 */
public class CloseUtils {
    public static void close(Closeable... closeables){
        if(closeables == null){
            return;
        }
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
