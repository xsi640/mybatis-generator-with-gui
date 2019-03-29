package com.suyang.mbg;

import com.suyang.mbg.domain.Property;
import com.suyang.mbg.enums.JavaType;
import com.suyang.mbg.enums.JdbcType;
import com.suyang.mbg.generator.*;
import com.suyang.mbg.generator.mapper.*;
import com.suyang.mbg.utils.SerializeUtils;
import freemarker.template.Configuration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
//        cfg.setDirectoryForTemplateLoading(new File("/m/develop/github/mybatis-generator/src/main/resources/template"));
//        cfg.setDefaultEncoding("utf-8");
//        BaseGenerator generator = new MapperGenerator(cfg);
//        generator.process(new OutputStreamWriter(System.out), getMapperGenConfig());
//
//        Root root = new Root();
//        Sql sql = new Sql();
//        sql.setValue("id,name,xxx");
//        Mapper mapper = new Mapper();
//        mapper.setNamespace("com.suyang");
//        mapper.setSql(sql);
//        root.setMapper(mapper);
//
//        ResultMap resultMap = new ResultMap();
//        resultMap.setType("com.suyang.domain.User");
//        Constructor constructor = new Constructor();
//        resultMap.setConstructor(constructor);
//        List<Arg> args0 = new ArrayList<>();
//        constructor.setArgs(args0);
//        args0.add(new IdArg("User", "String", "VARCHAR"));
//        args0.add(new Arg("User", "String", "VARCHAR"));
//        args0.add(new Arg("User", "String", "VARCHAR"));
//
//        root.setResultMap(resultMap);
//
//        List<Select> selects = new ArrayList<>();
//        selects.add(new Select("1", "pt", "sql a"));
//        selects.add(new Select("2", "pt", "sql a"));
//        selects.add(new Select("3", "pt", "sql a"));
//        root.setSelects(selects);
//
//        SerializeUtils.xmlSerializeByJAXBToFile("/m/downloads/1.txt", root);
    }

//    private static EntityGenConfig getEntity() {
//        EntityGenConfig result = new EntityGenConfig();
//        result.setBasePackage("com.suyang");
//        result.setEntityNamespace("domain");
//        result.setClassName("User");
//        result.setPrimaryKey(new Property("id", JavaType.Integer, JdbcType.INTEGER));
//        result.getProperties().add(new Property("name", JavaType.String, JdbcType.VARCHAR));
//        result.getProperties().add(new Property("age", JavaType.Integer, JdbcType.INTEGER));
//        result.getProperties().add(new Property("birthday", JavaType.Date, JdbcType.DATE));
//        return result;
//    }
//
//    private static MapperGenConfig getMapperGenConfig() {
//        MapperGenConfig result = new MapperGenConfig();
//        result.setBasePackage("com.suyang");
//        result.setMapperNamespace("mapper");
//        result.setEntityName("User");
//        result.setSuffix("Mapper");
//        result.setPrimaryKeyType(JavaType.Integer);
//        return result;
//    }
}