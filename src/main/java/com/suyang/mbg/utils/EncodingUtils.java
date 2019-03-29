package com.suyang.mbg.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

public class EncodingUtils {
    public static final String DEFAULT_CHARSET_STR = "UTF-8";
    public static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_STR);

    public static String encode(byte[] data, Charset charset) {
        return new String(data, charset);
    }

    public static String encode(byte[] data) {
        return encode(data, DEFAULT_CHARSET);
    }

    public static byte[] decode(String text, Charset charset) {
        return text.getBytes(charset);
    }

    public static byte[] decode(String text) {
        return decode(text, DEFAULT_CHARSET);
    }

    public static String base64Encode(byte[] data) {
        return Base64.encodeBase64String(data);
    }

    public static byte[] base64Decode(String data) {
        return Base64.decodeBase64(data);
    }

    public static String urlEncode(String url) {
        String result = "";
        try {
            result = URLEncoder.encode(url, DEFAULT_CHARSET_STR).replace("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String urlDecode(String url) {
        String result = "";
        try {
            result = URLDecoder.decode(url.replace("%20", "\\+"), DEFAULT_CHARSET_STR);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
