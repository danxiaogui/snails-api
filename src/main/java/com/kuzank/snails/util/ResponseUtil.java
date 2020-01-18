package com.kuzank.snails.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kuzank.snails.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/3
 */
public class ResponseUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseUtil.class);


    public static void writeJson(HttpServletResponse servletResponse, Result result) {

        servletResponse.setStatus(HttpStatus.OK.value());

        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json; charset=utf-8");

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
        String json = gson.toJson(result, Result.class);

        PrintWriter writer = null;
        try {
            writer = servletResponse.getWriter();
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("ResponseUtil writeJson error: ");
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

}
