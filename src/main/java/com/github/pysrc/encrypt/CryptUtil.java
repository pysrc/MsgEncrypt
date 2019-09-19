package com.github.pysrc.encrypt;

import java.util.Collection;

/**
 * @ClassName:
 * @Auther L.Chen
 * @CreateDate
 * @Description TODO
 */
public class CryptUtil {

    public static void encrypt(Object obj) throws Exception {
        // 判断是否集合类
        if (obj instanceof Collection) {
            Collection cols = (Collection) obj;
            for (Object col : cols) {
                encrypt(col);
            }
        } else {
            if (obj instanceof MsgEncrypt) {
                MsgEncrypt key = (MsgEncrypt) obj;
                String encrypt = key.encrypt();
                encrypt = encrypt + Config.getSplit() + System.currentTimeMillis();
                key.set_code(AES.AESEncode(encrypt));
            }
        }
    }

    public static boolean decrypt(Object obj) throws Exception {
        // 判断是否集合类
        if (obj instanceof Collection) {
            Collection cols = (Collection) obj;
            for (Object col : cols) {
                if (!decrypt(col)) {
                    return false;
                }
            }
        } else {
            if (obj instanceof MsgEncrypt) {
                MsgEncrypt key = (MsgEncrypt) obj;
                String decode = AES.AESDncode(key.get_code());
                String[] split = decode.split(Config.getSplit());
                String tm = split[split.length - 1];
                if (System.currentTimeMillis() - new Long(tm) > Config.getDt()) {
                    // 超时
                    return false;
                }
                key.decrypt(split[0]);
            }
        }
        return true;
    }
}
