package com.suyang.mbg.generator.xml;

import com.suyang.commons.NameUtils;
import com.suyang.commons.xml.Document;
import com.suyang.commons.xml.Element;
import com.suyang.commons.xml.TextElement;
import com.suyang.commons.xml.XmlElement;
import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.domain.PrimaryKey;
import com.suyang.mbg.domain.Property;
import com.suyang.mbg.generator.factory.SqlGeneratorFactory;
import com.suyang.mbg.generator.sql.SqlGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class XmlGenerator {

    public Document convert(GeneratorConfig config) {
        Document doc = new Document("-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
        doc.setRootElement(root(config));
        return doc;
    }

    private XmlElement root(GeneratorConfig config) {
        XmlElement root = new XmlElement("mapper");
        root.addAttribute("namespace", config.getMapperPackage() + "." + config.getMapperName());
        root.addElement(sqlElement(config));
        root.addElement(resultMapElement(config));

        SqlGenerator<XmlElement> sqlGenerator = SqlGeneratorFactory.getInstance().getXmlSqlGenerator();
        for (Method method : sqlGenerator.getClass().getDeclaredMethods()) {
            if (method.getReturnType().equals(Element.class)) {
                try {
                    root.addElement((XmlElement) method.invoke(sqlGenerator, config));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return root;
    }

    private Element resultMapElement(GeneratorConfig config) {
        XmlElement resultMapElement = new XmlElement("resultMap");
        resultMapElement.addAttribute("id", "BaseResultMap");
        resultMapElement.addAttribute("type", config.getEntityPackage() + "." + config.getEntityName());
        resultMapElement.addElement(propertyElement(config.getPrimaryKey()));
        for (Property property : config.getProperties())
            resultMapElement.addElement(propertyElement(property));
        return resultMapElement;
    }

    private Element propertyElement(Property property) {
        XmlElement propertyElement = new XmlElement(property instanceof PrimaryKey ? "id" : "result");
        propertyElement.addAttribute("property", property.getName());
        propertyElement.addAttribute("column", property.getDbName());
        propertyElement.addAttribute("javaType", property.getType());
        propertyElement.addAttribute("jdbcType", property.getJdbcType().name());
        return propertyElement;
    }

    private Element sqlElement(GeneratorConfig config) {
        XmlElement sqlElement = new XmlElement("sql");
        sqlElement.addAttribute("id", "Base_Column_List");
        sqlElement.addElement(new TextElement(getSqlValue(config)));
        return sqlElement;
    }

    private String getSqlValue(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder();
        sb.append(config.getPrimaryKey().getDbName()).append(", ");
        List<Property> properties = config.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            if (i == properties.size() - 1) {
                sb.append(NameUtils.toCamelName(property.getDbName()));
            } else {
                sb.append(NameUtils.toCamelName(property.getDbName())).append(", ");
            }
        }
        return sb.toString();
    }
}
