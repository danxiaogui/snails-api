package com.kuzank.snails.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/10
 */
public class GsonUtil {

    public static Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();


    public static Map objectToMap(Object o) {
        return gson.fromJson(gson.toJson(o), Map.class);
    }

    public static String fromJson(Object o) {
        return gson.toJson(o);
    }

}
