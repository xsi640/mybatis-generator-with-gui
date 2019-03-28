package com.suyang.mbg.domain;

import com.suyang.mbg.enums.JavaType;
import com.suyang.mbg.enums.JdbcType;

public class PrimaryKey extends Property {
    public PrimaryKey(String name, JavaType type, JdbcType jdbcType) {
        super(name, type, jdbcType);
    }
}
