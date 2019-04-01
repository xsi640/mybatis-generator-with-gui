package com.suyang.commons;

import java.lang.annotation.Annotation;

public class EnumsUtils {
    public static <T extends Annotation> T getAnnotation(Enum value, Class<T> clazz) throws NoSuchFieldException {
        return value.getClass().getField(value.name()).getAnnotation(clazz);
    }
}
