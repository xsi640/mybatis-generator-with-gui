package com.suyang.mbg.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {
    static {
        JSON.DEFAULT_GENERATE_FEATURE &= ~SerializerFeature.WriteEnumUsingName.mask;
    }

    public static String toString(Object obj) {
        return JSON.toJSONString(obj);
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(String json) {
        return (T) JSON.parse(json);
    }

    public static <T> T parse(String json, Class<T> clazz) {
        return (T) JSON.parseObject(json, clazz);
    }

    public static JSONObject parseToJSONObject(String json) {
        return JSON.parseObject(json);
    }

    public static JSONArray parseToJsonArray(String json) {
        return JSON.parseArray(json);
    }

    public static Object parseToJSON(String json) {
        try {
            return parseToJsonArray(json);
        } catch (JSONException e) {
            return parseToJSONObject(json);
        }
    }
}
