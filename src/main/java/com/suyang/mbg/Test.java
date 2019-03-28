package com.suyang.mbg;

import com.suyang.mbg.domain.Property;
import com.suyang.mbg.enums.JavaType;
import com.suyang.mbg.enums.JdbcType;
import com.suyang.mbg.generator.*;
import freemarker.template.Configuration;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("/m/develop/github/mybatis-generator/src/main/resources/template"));
        cfg.setDefaultEncoding("utf-8");
        BaseGenerator generator = new MapperGenerator(cfg);
        generator.process(new OutputStreamWriter(System.out), getMapperGenConfig());
    }

    private static EntityGenConfig getEntity() {
        EntityGenConfig result = new EntityGenConfig();
        result.setBasePackage("com.suyang");
        result.setEntityNamespace("domain");
        result.setClassName("User");
        result.setPrimaryKey(new Property("id", JavaType.Integer, JdbcType.INTEGER));
        result.getProperties().add(new Property("name", JavaType.String, JdbcType.VARCHAR));
        result.getProperties().add(new Property("age", JavaType.Integer, JdbcType.INTEGER));
        result.getProperties().add(new Property("birthday", JavaType.Date, JdbcType.DATE));
        return result;
    }

    private static MapperGenConfig getMapperGenConfig() {
        MapperGenConfig result = new MapperGenConfig();
        result.setBasePackage("com.suyang");
        result.setMapperNamespace("mapper");
        result.setEntityName("User");
        result.setSuffix("Mapper");
        result.setPrimaryKeyType(JavaType.Integer);
        return result;
    }
}