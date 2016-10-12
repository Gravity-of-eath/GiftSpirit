package com.yaokun.giftspirit.Parser;

import com.google.gson.Gson;

/**
 * Created by 姚 坤 on 2016/9/20.
 */
public class Parser {
    static Gson gson;

    public static <T> T JsonToObj(String json, Class<T> clazz) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.fromJson(json, clazz);
    }

}
