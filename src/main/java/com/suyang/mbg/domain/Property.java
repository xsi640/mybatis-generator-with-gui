package com.suyang.mbg.domain;

import com.suyang.mbg.enums.JavaType;
import com.suyang.mbg.enums.JdbcType;

public class Property {
    private String name;
    private JavaType type;
    private JdbcType jdbcType;

    public Property() {
    }

    public Property(String name, JavaType type, JdbcType jdbcType) {
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
