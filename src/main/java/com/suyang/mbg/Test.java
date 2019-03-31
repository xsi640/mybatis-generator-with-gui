package com.suyang.mbg;

import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> nameToType = new HashMap<>();
        nameToType.put("ARRAY", 2003);
        nameToType.put("BIGINT", -5);
        nameToType.put("BINARY", -2);
        nameToType.put("BIT", -7);
        nameToType.put("BLOB", 2004);
        nameToType.put("BOOLEAN", 16);
        nameToType.put("CHAR", 1);
        nameToType.put("CLOB", 2005);
        nameToType.put("DATALINK", 70);
        nameToType.put("DATE", 91);
        nameToType.put("DECIMAL", 3);
        nameToType.put("DISTINCT", 2001);
        nameToType.put("DOUBLE", 8);
        nameToType.put("FLOAT", 6);
        nameToType.put("INTEGER", 4);
        nameToType.put("JAVA_OBJECT", 2000);
        nameToType.put("LONGVARBINARY", -4);
        nameToType.put("LONGVARCHAR", -1);
        nameToType.put("NCHAR", -15);
        nameToType.put("NCLOB", 2011);
        nameToType.put("NVARCHAR", -9);
        nameToType.put("LONGNVARCHAR", -16);
        nameToType.put("NULL", 0);
        nameToType.put("NUMERIC", 2);
        nameToType.put("OTHER", 1111);
        nameToType.put("REAL", 7);
        nameToType.put("REF", 2006);
        nameToType.put("SMALLINT", 5);
        nameToType.put("STRUCT", 2002);
        nameToType.put("TIME", 92);
        nameToType.put("TIMESTAMP", 93);
        nameToType.put("TINYINT", -6);
        nameToType.put("VARBINARY", -3);
        nameToType.put("VARCHAR", 12);

        for (String key : nameToType.keySet()) {
            System.out.println("@Value(" + nameToType.get(key) + ")");
            System.out.println(key + ",");
        }


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
//        args0.put(new IdArg("User", "String", "VARCHAR"));
//        args0.put(new Arg("User", "String", "VARCHAR"));
//        args0.put(new Arg("User", "String", "VARCHAR"));
//
//        root.setResultMap(resultMap);
//
//        List<Select> selects = new ArrayList<>();
//        selects.put(new Select("1", "pt", "sql a"));
//        selects.put(new Select("2", "pt", "sql a"));
//        selects.put(new Select("3", "pt", "sql a"));
//        root.setSelects(selects);
//
//        SerializeUtils.xmlSerializeByJAXBToFile("/m/downloads/1.txt", root);
    }

//    private static EntityGenConfig getEntity() {
//        EntityGenConfig result = new EntityGenConfig();
//        result.setBasePackage("com.suyang");
//        result.setEntityPackage("domain");
//        result.setEntityName("User");
//        result.setPrimaryKey(new Property("id", JavaType.Integer, JdbcType.INTEGER));
//        result.getProperties().put(new Property("name", JavaType.String, JdbcType.VARCHAR));
//        result.getProperties().put(new Property("age", JavaType.Integer, JdbcType.INTEGER));
//        result.getProperties().put(new Property("birthday", JavaType.Date, JdbcType.DATE));
//        return result;
//    }
//
//    private static MapperGenConfig getMapperGenConfig() {
//        MapperGenConfig result = new MapperGenConfig();
//        result.setBasePackage("com.suyang");
//        result.setMapperPackage("mapper");
//        result.setEntityName("User");
//        result.setSuffix("Mapper");
//        result.setPrimaryKeyType(JavaType.Integer);
//        return result;
//    }
}