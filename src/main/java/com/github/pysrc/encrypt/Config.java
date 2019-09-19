package com.github.pysrc.encrypt;

public class Config {
    private static long dt = 1000 * 60 * 30; // 30分钟的过期时间
    private static String key = "L.Chen"; // 加密的key
    private static boolean done = false; // 是否完成初始化
    private static String split = "_:_"; // 加密信息与时间撮的分隔符

    public static void done() throws Exception {
        Config.done = true;
        AES.init(key);
    }

    public static long getDt() {
        return dt;
    }

    public static void setDt(long dt) {
        if (!done) {
            Config.dt = dt;
        }
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        if (!done) {
            Config.key = key;
        }
    }

    public static String getSplit() {
        return split;
    }

    public static void setSplit(String split) {
        if (!done) {
            Config.split = split;
        }
    }
}
