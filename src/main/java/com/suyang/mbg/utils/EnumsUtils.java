package com.suyang.mbg.utils;

import java.lang.annotation.Annotation;

public class EnumsUtils {
    public static <T extends Annotation> T getService(Enum value, Class<T> clazz) throws NoSuchFieldException {
        return value.getClass().getField(value.name()).getAnnotation(clazz);
    }
}
