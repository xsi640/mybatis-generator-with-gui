package com.suyang.mbg.generator.domain;

import com.suyang.mbg.enums.JavaType;
import com.suyang.mbg.database.enums.JdbcType;

public class PrimaryKey extends Property {

    private boolean autoIncrement = false;

    public PrimaryKey(String name, String dbName, JavaType type, JdbcType jdbcType, boolean autoIncrement) {
        super(name, dbName, type, jdbcType);
        this.autoIncrement = autoIncrement;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
}
