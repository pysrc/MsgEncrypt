package com.github.pysrc.encrypt;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Auther L.Chen
 * @CreateDate
 * @Description TODO
 */
class A implements MsgEncrypt {

    private Long key1;
    private String key2;
    private String code;

    public A() {
    }

    public A(Long key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public String toString() {
        return key1 + ":" + key2 + ":" + code;
    }

    @Override
    public String encrypt() {
        return key1 + ":" + key2;
    }

    @Override
    public void decrypt(String decode) {
        String[] split = decode.split(":");
        key1 = new Long(split[0]);
        key2 = split[1];
    }

    @Override
    public void set_code(String code) {
        this.code = code;
    }

    @Override
    public String get_code() {
        return code;
    }

    // getter/setter

    public Long getKey1() {
        return key1;
    }

    public void setKey1(Long key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Config.setDt(1000 * 1);
        Config.setKey("L.Chen");
        Config.done();

//        one();
//        overtime();
        muti();
    }

    // 单个加/解密
    static void one() throws Exception {
        A a = new A(112L, "Hello");
        CryptUtil.encrypt(a);
        a.setKey1(null);
        a.setKey2(null);
        CryptUtil.decrypt(a);
        boolean decrypt = CryptUtil.decrypt(a);
        System.out.println(decrypt);
        System.out.println(a);
    }

    // 超时
    static void overtime() throws Exception {
        A a = new A(888L, "Hello");
        CryptUtil.encrypt(a);
        a.setKey1(null);
        a.setKey2(null);
        Thread.sleep(1000 * 2);
        boolean decrypt = CryptUtil.decrypt(a);
        System.out.println(decrypt);
        System.out.println(a);
    }

    // 集合类加/解密
    static void muti() throws Exception {
        List<A> list = new ArrayList<>();
        for (long i = 0; i < 1000; i++) {
            list.add(new A(i, i + "hh"));
        }
        long s = System.currentTimeMillis();
        CryptUtil.encrypt(list);
        System.out.println(System.currentTimeMillis() - s);
        for (A a : list) {
            a.setKey1(null);
            a.setKey2(null);
        }
        System.out.println(list);
        s = System.currentTimeMillis();
        boolean decrypt = CryptUtil.decrypt(list);
        System.out.println(System.currentTimeMillis() - s);
        if (decrypt) {
            System.out.println(list);
        }
    }
}
