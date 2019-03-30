package com.suyang.mbg.generator.domain;

import com.suyang.mbg.enums.JavaType;
import com.suyang.mbg.database.enums.JdbcType;

public class Property {
    private String name;
    private String dbName;
    private JavaType type;
    private JdbcType jdbcType;

    public Property() {
    }

    public Property(String name, String dbName, JavaType type, JdbcType jdbcType) {
        this.name = name;
        this.type = type;
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

    public JavaType getType() {
        return type;
    }

    public void setType(JavaType type) {
        this.type = type;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(JdbcType jdbcType) {
        this.jdbcType = jdbcType;
    }
}
