package com.suyang.mbg.generator.sql;

import com.suyang.commons.NameUtils;
import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.domain.Property;

public class SqlUtils {
    public static String getColumns(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder();
        sb.append(config.getPrimaryKey().getDbName()).append(", ");
        for (int i = 0; i < config.getProperties().size(); i++) {
            Property property = config.getProperties().get(i);
            if (i != config.getProperties().size() - 1) {
                sb.append(property.getDbName()).append(", ");
            } else {
                sb.append(property.getDbName());
            }
        }
        return sb.toString();
    }

    public static String getProperties(GeneratorConfig config, String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("#{").append(prefix)
                .append(NameUtils.toCamelName(config.getPrimaryKey().getName()))
                .append("}, ");
        for (int i = 0; i < config.getProperties().size(); i++) {
            Property property = config.getProperties().get(i);
            if (i != config.getProperties().size() - 1) {
                sb.append("#{").append(prefix)
                        .append(NameUtils.toCamelName(property.getName()))
                        .append("}, ");
            } else {
                sb.append("#{").append(prefix)
                        .append(NameUtils.toCamelName(property.getDbName()))
                        .append("}");
            }
        }
        return sb.toString();
    }

    public static String getUpdateSet(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < config.getProperties().size(); i++) {
            Property property = config.getProperties().get(i);
            sb.append(property.getDbName());
            sb.append("=");
            sb.append("#{").append(NameUtils.toCamelName(property.getName())).append("}");

            if (i != config.getProperties().size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
