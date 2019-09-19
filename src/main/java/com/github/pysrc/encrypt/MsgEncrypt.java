package com.github.pysrc.encrypt;

/**
 * @ClassName:
 * @Auther L.Chen
 * @CreateDate
 * @Description TODO
 */
public interface MsgEncrypt {

    /**
     * 加密时回调
     * @return 待加密信息
     */
    String encrypt();
    /**
     * 解密时回调
     * @param decode 解密后的信息
     */
    void decrypt(String decode);

    /**
     * 存放加密后的信息
     * @param code 加密信息
     */
    void set_code(String code);

    /**
     * 获取加密后信息
     * @return 加密信息
     */
    String get_code();
}
