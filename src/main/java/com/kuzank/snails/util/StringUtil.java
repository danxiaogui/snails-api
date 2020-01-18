package com.kuzank.snails.util;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/10
 */
public class StringUtil {

    public static boolean isNotBlank(Object cs) {
        return cs != null && !isBlank(cs.toString());
    }
}
