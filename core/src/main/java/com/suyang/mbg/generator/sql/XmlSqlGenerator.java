package com.suyang.mbg.generator.sql;

import com.suyang.commons.Strings;
import com.suyang.commons.xml.Element;
import com.suyang.commons.xml.TextElement;
import com.suyang.commons.xml.XmlElement;
import com.suyang.mbg.domain.GeneratorConfig;

public class XmlSqlGenerator implements SqlGenerator<Element> {

    @Override
    public Element insert(GeneratorConfig config) {
        XmlElement result = new XmlElement("insert");
        result.addAttribute("id", "insert");
        result.addAttribute("keyProperty", config.getPrimaryKey().getName());
        result.addAttribute("parameterType", config.getEntityPackage() + "." + config.getEntityName());
        if (config.getPrimaryKey().isAutoIncrement()) {
            result.addAttribute("useGeneratedKeys", "true");
        }
        result.addElement(new TextElement(Strings.format("insert into {table} ({columns}) value({values})")
                .with("table", config.getTableName())
                .with("columns", SqlUtils.getColumns(config))
                .with("values", SqlUtils.getProperties(config, "")).build()));
        return result;
    }

    @Override
    public Element insertCollection(GeneratorConfig config) {
        XmlElement result = new XmlElement("insert");
        result.addAttribute("id", "insertCollection");
        result.addAttribute("keyProperty", config.getPrimaryKey().getName());
        result.addAttribute("parameterType", "java.util.List");
        if (config.getPrimaryKey().isAutoIncrement()) {
            result.addAttribute("useGeneratedKeys", "true");
        }
        result.addElement(new TextElement(Strings.format("insert into {table} ({columns}) values")
                .with("table", config.getTableName())
                .with("columns", SqlUtils.getColumns(config)).build()));
        XmlElement foreach = new XmlElement("foreach");
        foreach.addAttribute("collection", "list");
        foreach.addAttribute("item", "item");
        foreach.addAttribute("index", "index");
        foreach.addAttribute("separator", ",");
        foreach.addElement(new TextElement(Strings.format("({values})")
                .with("values", SqlUtils.getProperties(config, "item.")).build()));
        result.addElement(foreach);
        return result;
    }

    @Override
    public Element insertOrUpdate(GeneratorConfig config) {
        XmlElement result = new XmlElement("insert");
        result.addAttribute("id", "insertOrUpdate");
        result.addAttribute("keyProperty", config.getPrimaryKey().getName());
        result.addAttribute("parameterType", config.getEntityPackage() + "." + config.getEntityName());
        if (config.getPrimaryKey().isAutoIncrement()) {
            result.addAttribute("useGeneratedKeys", "true");
        }
        result.addElement(new TextElement(Strings.format("insert into {table} ({columns}) values({values}) on duplicate key update {update}")
                .with("table", config.getTableName())
                .with("columns", SqlUtils.getColumns(config))
                .with("values", SqlUtils.getProperties(config, ""))
                .with("update", SqlUtils.getUpdateSet(config)).build()));
        return result;
    }

    @Override
    public Element update(GeneratorConfig config) {
        XmlElement result = new XmlElement("update");
        result.addAttribute("id", "update");
        result.addAttribute("parameterType", config.getEntityPackage() + "." + config.getEntityName());
        result.addElement(new TextElement(Strings.format("update {table} set {update} where {id}=#{{entityId}}")
                .with("table", config.getTableName())
                .with("update", SqlUtils.getUpdateSet(config))
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build()));
        return result;
    }

    @Override
    public Element delete(GeneratorConfig config) {
        XmlElement result = new XmlElement("delete");
        result.addAttribute("id", "delete");
        result.addAttribute("parameterType", config.getPrimaryKey().getType());
        result.addElement(new TextElement(Strings.format("delete from {table} where {id}=#{{entityId}}")
                .with("table", config.getTableName())
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build()));
        return result;
    }

    @Override
    public Element deleteAll(GeneratorConfig config) {
        XmlElement result = new XmlElement("delete");
        result.addAttribute("id", "deleteAll");
        result.addElement(new TextElement(
                Strings.format("delete from {table}")
                        .with("table", config.getTableName()).build()));
        return result;
    }

    @Override
    public Element deletes(GeneratorConfig config) {
        XmlElement result = new XmlElement("delete");
        result.addAttribute("id", "deletes");
        result.addAttribute("parameterType", "java.util.List");
        result.addElement(new TextElement(Strings.format("delete from {table} where {id} in")
                .with("table", config.getTableName())
                .with("id", config.getPrimaryKey().getDbName()).build()));

        XmlElement foreach = new XmlElement("foreach");
        foreach.addAttribute("collection", "list");
        foreach.addAttribute("item", "item");
        foreach.addAttribute("open", "(");
        foreach.addAttribute("separator", ",");
        foreach.addAttribute("close", ")");
        foreach.addElement(new TextElement("#{item}"));

        result.addElement(foreach);

        return result;
    }

    @Override
    public Element count(GeneratorConfig config) {
        XmlElement result = new XmlElement("select");
        result.addAttribute("id", "count");
        result.addAttribute("resultType", "java.lang.Integer");
        result.addElement(new TextElement(Strings.format("select count(*) from {table}")
                .with("table", config.getTableName()).build()));

        return result;
    }

    @Override
    public Element findAll(GeneratorConfig config) {
        XmlElement result = new XmlElement("select");
        result.addAttribute("id", "findAll");
        result.addAttribute("resultMap", "BaseResultMap");
        result.addElement(new TextElement("select "));
        result.addElement(new XmlElement("include").withAttribute("refid", "Base_Column_List"));
        result.addElement(new TextElement(Strings.format(" from {table}")
                .with("table", config.getTableName()).build()));

        return result;
    }

    @Override
    public Element findById(GeneratorConfig config) {
        XmlElement result = new XmlElement("select");
        result.addAttribute("id", "findById");
        result.addAttribute("resultMap", "BaseResultMap");
        result.addAttribute("parameterType", config.getPrimaryKey().getType());
        result.addElement(new TextElement("select "));
        result.addElement(new XmlElement("include").withAttribute("refid", "Base_Column_List"));
        result.addElement(new TextElement(Strings.format(" from {table} where {id}=#{{entityId}}")
                .with("table", config.getTableName())
                .with("id", config.getPrimaryKey().getDbName())
                .with("entityId", config.getPrimaryKey().getName()).build()));

        return result;
    }
}
