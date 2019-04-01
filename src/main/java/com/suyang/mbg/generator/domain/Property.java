package com.suyang.mbg.generator.domain;

import com.suyang.mbg.database.enums.JdbcType;

public class Property {
    private String name;
    private String dbName;
    private JdbcType jdbcType;

    public Property() {
    }

    public Property(String name, String dbName, JdbcType jdbcType) {
        this.name = name;
        this.dbName = dbName;
        this.jdbcType = jdbcType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(JdbcType jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getType() {
        switch (this.jdbcType) {
            case BIT:
            case BOOLEAN:
                return "java.lang.Boolean";
            case TINYINT:
                return "java.lang.Byte";
            case BLOB:
            case BINARY:
            case VARBINARY:
            case LONGVARBINARY:
                return "byte[]";
            case TIMESTAMP:
            case TIME:
            case DATE:
                return "java.util.Date";
            case FLOAT:
            case DOUBLE:
                return "java.lang.Double";
            case REAL:
                return "java.lang.Float";
            case INTEGER:
                return "java.lang.Integer";
            case BIGINT:
                return "java.lang.Long";
            case ARRAY:
            case JAVA_OBJECT:
            case NULL:
            case DISTINCT:
            case OTHER:
            case REF:
            case DATALINK:
            case STRUCT:
                return "java.lang.Object";
            case SMALLINT:
                return "java.lang.Short";
            case CHAR:
            case NCHAR:
            case NVARCHAR:
            case NCLOB:
            case CLOB:
            case LONGVARCHAR:
            case LONGNVARCHAR:
            case VARCHAR:
                return "java.lang.String";
        }
        throw new RuntimeException("The jdbc type is not support.");
    }
}
