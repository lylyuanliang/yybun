package com.yybun.common.utils;

import java.util.UUID;

/**
 * uuid工具类
 */
public class UUIDUtils {

    /**
     * 生成32位 uuid
     * @return
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
