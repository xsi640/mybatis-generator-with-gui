package com.suyang.mbg.domain;

import com.suyang.mbg.enums.JdbcType;

public class PrimaryKey extends Property {

    private boolean autoIncrement = false;

    public PrimaryKey(String name, String dbName, JdbcType jdbcType, boolean autoIncrement) {
        super(name, dbName, jdbcType);
        this.autoIncrement = autoIncrement;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
}
