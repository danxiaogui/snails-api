package com.kuzank.snails.util;

import java.util.UUID;

/**
 * <p>Description: </p>
 *
 * @author kuzank
 */
public class UuidUtil {

    /**
     * @return 获取 32 位 UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
