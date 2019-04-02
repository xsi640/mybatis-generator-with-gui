package com.suyang.mbg.generator.sql;

import com.suyang.commons.Strings;
import com.suyang.commons.xml.OutputUtilities;
import com.suyang.mbg.domain.GeneratorConfig;

public class AnnotationSqlGenerator implements SqlGenerator<String> {

    private String indent = "    ";

    public AnnotationSqlGenerator() {
    }

    public AnnotationSqlGenerator(String indent) {
        this.indent = indent;
    }

    @Override
    public String insert(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Insert(\"insert into {table} ({columns}) value({values})\")")
                .with("table", config.getTableName())
                .with("columns", SqlUtils.getColumns(config))
                .with("values", SqlUtils.getProperties(config, "")).build());
        OutputUtilities.newLine(sb).append(indent);
        sb.append(getOptions(config));
        return sb.toString();
    }

    @Override
    public String insertCollection(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append("@Insert({");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"<script>\",");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append(Strings.format("\"insert into {table} ({columns}) values\",")
                .with("table", config.getTableName())
                .with("columns", SqlUtils.getColumns(config)).build());
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"<foreach collection='list' item='item' index='index' separator=','>\",");
        sb.append(Strings.format("\"({values})\",")
                .with("values", SqlUtils.getProperties(config, "item.")).build());
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"</foreach>\",");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"</script>\"");
        OutputUtilities.newLine(sb).append(indent);
        sb.append("})");
        OutputUtilities.newLine(sb).append(indent);
        sb.append(getOptions(config));
        return sb.toString();
    }

    @Override
    public String insertOrUpdate(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Insert(\"insert into {table} ({columns}) values({values}) on duplicate key update {update}\")")
                .with("table", config.getTableName())
                .with("columns", SqlUtils.getColumns(config))
                .with("values", SqlUtils.getProperties(config, ""))
                .with("update", SqlUtils.getUpdateSet(config)).build());
        OutputUtilities.newLine(sb).append(indent);
        sb.append(getOptions(config));
        return sb.toString();
    }

    @Override
    public String update(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Update(\"update {table} set {update} where {id}=#{{entityId}}\")")
                .with("table", config.getTableName())
                .with("update", SqlUtils.getUpdateSet(config))
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build());
        OutputUtilities.newLine(sb).append(indent);
        sb.append(getOptions(config));
        return sb.toString();
    }

    @Override
    public String delete(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Delete(\"delete from {table} where {id}=#{{entityId}}\")")
                .with("table", config.getTableName())
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build());
        return sb.toString();
    }

    @Override
    public String deleteAll(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Delete(\"delete from {table}\")")
                .with("table", config.getTableName()).build());
        return sb.toString();
    }

    @Override
    public String deletes(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append("@Delete({");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"<script>\",");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append(Strings.format("\"delete from {table} where {id} in\",")
                .with("table", config.getTableName())
                .with("id", config.getPrimaryKey().getDbName()).build());
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"<foreach collection='list' item='item' open='(' separator=',' close=')' >\",");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"#{item}\",");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"</foreach>\",");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append(" \"</script>\"");
        OutputUtilities.newLine(sb).append(indent);
        sb.append("})");

        return sb.toString();
    }

    @Override
    public String findAll(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Select(\"select * from {table}\")")
                .with("table", config.getTableName()).build());
        return sb.toString();
    }

    @Override
    public String findById(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Select(\"select * from {table} where {id}=#{{entityId}}\")")
                .with("table", config.getTableName())
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build());
        return sb.toString();
    }

    @Override
    public String count(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Select(\"select count(*) from {table}\")")
                .with("table", config.getTableName()).build());
        return sb.toString();
    }

    private String getOptions(GeneratorConfig config) {
        return Strings.format("@Options(useGeneratedKeys={autoIncrement}, keyProperty=\"{id}\", keyColumn=\"{entityId}\")")
                .with("autoIncrement", String.valueOf(config.getPrimaryKey().isAutoIncrement()).toLowerCase())
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build();
    }
}
