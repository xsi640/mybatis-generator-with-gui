package com.suyang.mbg.generator.sql;

import com.suyang.mbg.generator.XmlGenConfig;
import com.suyang.mbg.generator.domain.Property;
import com.suyang.mbg.utils.NameUtils;

public class XmlSqlGenerator implements SqlGenerator {

    private static final String COLUMN_LIST = "<include refid=\"Base_Column_List\"/>";
    private static final String FOREACH_INSERT_START = "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">";
    private static final String FOREACH_DELETE_START = "<foreach collection=\"list\"  item=\"item\" open=\"(\" separator=\",\" close=\")\">";
    private static final String FOREACH_END = "</foreach>";

    @Override
    public String insert(XmlGenConfig config) {
        return String.format("insert into %s (%s) values(%s)",
                config.getTableName(),
                getColumns(config),
                getProperties(config, ""));
    }

    @Override
    public String insertCollection(XmlGenConfig config) {
        return String.format("insert into %s (%s) values" +
                        FOREACH_INSERT_START + " (%s) " + FOREACH_END,
                config.getTableName(),
                getColumns(config),
                getProperties(config, "item."));
    }

    @Override
    public String update(XmlGenConfig config) {
        return String.format("update %s set %s where %s=#{%s}",
                config.getTableName(),
                getUpdateSet(config),
                config.getPrimaryKey().getDbName(),
                config.getPrimaryKey().getName());
    }

    @Override
    public String delete(XmlGenConfig config) {
        return String.format("delete from %s where %s=#{%s}",
                config.getTableName(),
                config.getPrimaryKey().getDbName(),
                config.getPrimaryKey().getName());
    }

    @Override
    public String deleteAll(XmlGenConfig config) {
        return String.format("delete from %s", config.getTableName());
    }

    @Override
    public String deletes(XmlGenConfig config) {
        return String.format("delete from %s where %s in " +
                        FOREACH_DELETE_START + "#{item}" + FOREACH_END,
                config.getTableName(),
                config.getPrimaryKey().getDbName());
    }

    @Override
    public String count(XmlGenConfig config) {
        return String.format("select count(*) from %s", config.getTableName());
    }

    @Override
    public String findAll(XmlGenConfig config) {
        return String.format("select %s from %s",
                COLUMN_LIST,
                config.getTableName());
    }

    @Override
    public String findById(XmlGenConfig config) {
        return String.format("select %s from %s where %s = #{%s}",
                COLUMN_LIST,
                config.getTableName(),
                config.getPrimaryKey().getDbName(),
                config.getPrimaryKey().getName());
    }

    private String getColumns(XmlGenConfig config) {
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

    private String getProperties(XmlGenConfig config, String prefix) {
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

    private String getUpdateSet(XmlGenConfig config) {
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
