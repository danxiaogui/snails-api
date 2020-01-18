package com.kuzank.snails.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/10
 */
public class RequestUtil {

    public static String getToken(HttpServletRequest servletRequest) {
        return servletRequest.getHeader("token");
    }
}
