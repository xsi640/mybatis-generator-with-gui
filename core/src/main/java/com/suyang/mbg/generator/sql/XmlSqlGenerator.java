package com.suyang.mbg.generator.sql;

import com.suyang.commons.Strings;
import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.domain.Property;
import com.suyang.commons.NameUtils;

public class XmlSqlGenerator implements SqlGenerator {

    private static final String COLUMN_LIST = "<include refid=\"Base_Column_List\"/>";
    private static final String FOREACH_INSERT_START = "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">";
    private static final String FOREACH_DELETE_START = "<foreach collection=\"list\"  item=\"item\" open=\"(\" separator=\",\" close=\")\">";
    private static final String FOREACH_END = "</foreach>";

    @Override
    public String insert(GeneratorConfig config) {
        return Strings.format("insert into {table} ({columns}) value({values})")
                .with("table", config.getTableName())
                .with("columns", getColumns(config))
                .with("values", getProperties(config, "")).build();
    }

    @Override
    public String insertCollection(GeneratorConfig config) {
        return Strings.format("insert into {table} ({columns}) values {foreach} ({values}) {end}")
                .with("table", config.getTableName())
                .with("columns", getColumns(config))
                .with("foreach", FOREACH_INSERT_START)
                .with("values", getProperties(config, "item."))
                .with("end", FOREACH_END).build();
    }

    @Override
    public String insertOrUpdate(GeneratorConfig config) {
        return Strings.format("insert into {table} ({columns}) values({values}) on duplicate key update {update}")
                .with("table", config.getTableName())
                .with("columns", getColumns(config))
                .with("values", getProperties(config, ""))
                .with("update", getUpdateSet(config)).build();
    }

    @Override
    public String update(GeneratorConfig config) {
        return Strings.format("update {table} set {update} where {id}=#{{entityId}}")
                .with("table", config.getTableName())
                .with("update", getUpdateSet(config))
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build();
    }

    @Override
    public String delete(GeneratorConfig config) {
        return Strings.format("delete from {table} where {id}=#{{entityId}}")
                .with("table", config.getTableName())
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build();
    }

    @Override
    public String deleteAll(GeneratorConfig config) {
        return Strings.format("delete from {table}")
                .with("table", config.getTableName()).build();
    }

    @Override
    public String deletes(GeneratorConfig config) {
        return Strings.format("delete from {table} where {id} in {foreach} #{item} {end}")
                .with("table", config.getTableName())
                .with("id", config.getPrimaryKey().getDbName())
                .with("foreach", FOREACH_DELETE_START)
                .with("end", FOREACH_END).build();
    }

    @Override
    public String count(GeneratorConfig config) {
        return Strings.format("select count(*) from {table}")
                .with("table", config.getTableName()).build();
    }

    @Override
    public String findAll(GeneratorConfig config) {
        return Strings.format("select {columns} from {table}")
                .with("columns", COLUMN_LIST)
                .with("table", config.getTableName()).build();
    }

    @Override
    public String findById(GeneratorConfig config) {
        return Strings.format("select {columns} from {table} where {id}=#{{entityId}}")
                .with("columns", COLUMN_LIST)
                .with("table", config.getTableName())
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build();
    }

    private String getColumns(GeneratorConfig config) {
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

    private String getProperties(GeneratorConfig config, String prefix) {
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

    private String getUpdateSet(GeneratorConfig config) {
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
