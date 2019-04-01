package com.suyang.mbg.utils;

import org.apache.commons.lang3.StringUtils;

public class NameUtils {

    public static String toCamelName(String name) {
        String s = toPascalName(name);
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public static String toPascalName(String name) {
        if (name.contains("-") || name.contains("_")) {
            String[] ns = name.split("[-_]");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ns.length; i++) {
                String s = ns[i];
                if (StringUtils.isEmpty(s))
                    continue;
                sb.append(s.substring(0, 1).toUpperCase());
                sb.append(s.substring(1).toLowerCase());
            }
            return sb.toString();
        } else {
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
    }
}
