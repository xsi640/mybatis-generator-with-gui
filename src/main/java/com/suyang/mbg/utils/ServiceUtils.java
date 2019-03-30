package com.suyang.mbg.utils;

public class ServiceUtils {
    public static Class getService(Enum value) throws NoSuchFieldException {
        return value.getClass().getField(value.name()).getAnnotation(Service.class).clazz();
    }
}
