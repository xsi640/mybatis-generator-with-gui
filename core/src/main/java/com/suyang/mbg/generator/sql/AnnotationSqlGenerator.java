package com.suyang.mbg.generator.sql;

import com.suyang.commons.Strings;
import com.suyang.commons.xml.OutputUtilities;
import com.suyang.commons.xml.XmlElement;
import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.domain.Property;

@SuppressWarnings("Duplicates")
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
    public String insertOrUpdateCollection(GeneratorConfig config) {
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
        sb.append("on duplicate key update ");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append(config.getPrimaryKey().getDbName()).append("=").append("values(").append(config.getPrimaryKey().getName()).append(")");
        for (int i = 0; i < config.getProperties().size(); i++) {
            Property property = config.getProperties().get(i);
            sb.append(", ").append(property.getDbName()).append("=").append("values(").append(property.getName()).append(")");
        }
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"</script>\"");
        OutputUtilities.newLine(sb).append(indent);
        sb.append("})");
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
    public String findByWhere(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append("@Select({");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"<script>\",");
        sb.append(Strings.format("select * from {table} <if test='where != null and where != '''>where ${where}</if>")
                .with("table", config.getTableName()).build());
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append(" \"</script>\"");
        OutputUtilities.newLine(sb).append(indent);
        sb.append("})");

        return sb.toString();
    }

    @Override
    public String findByWhereOrder(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append("@Select({");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"<script>\",");
        sb.append(Strings.format("select * from {table} <if test='where != null and where != '''>where ${where}</if> <if test='order != null and order != '''>order by ${order}</if>")
                .with("table", config.getTableName()).build());
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append(" \"</script>\"");
        OutputUtilities.newLine(sb).append(indent);
        sb.append("})");

        return sb.toString();
    }

    @Override
    public String findByLimit(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Select(\"select * from {table} limit #{offset}, #{limit}\")")
                .with("table", config.getTableName()).build());
        return sb.toString();
    }

    @Override
    public String findByWhereLimit(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append("@Select({");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"<script>\",");
        sb.append(Strings.format("select * from {table} <if test='where != null and where != '''>where ${where}</if> limit #{offset}, #{limit}")
                .with("table", config.getTableName()).build());
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append(" \"</script>\"");
        OutputUtilities.newLine(sb).append(indent);
        sb.append("})");

        return sb.toString();
    }

    @Override
    public String findByWhereOrderLimit(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append("@Select({");
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append("\"<script>\",");
        sb.append(Strings.format("select * from {table} <if test='where != null and where != '''>where ${where}</if> <if test='order != null and order != '''>order by ${order}</if> limit #{offset}, #{limit}")
                .with("table", config.getTableName()).build());
        OutputUtilities.newLine(sb).append(indent).append(indent);
        sb.append(" \"</script>\"");
        OutputUtilities.newLine(sb).append(indent);
        sb.append("})");

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

    @Override
    public String countByWhere(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder(indent);
        sb.append(Strings.format("@Select(\"select count(*) from {table} <if test='where != null and where != '''>where ${where}</if>\")")
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
