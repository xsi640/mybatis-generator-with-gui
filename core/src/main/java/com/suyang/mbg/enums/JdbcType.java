package com.suyang.mbg.enums;

import com.suyang.commons.EnumsUtils;
import com.suyang.commons.Value;
import org.apache.commons.lang3.StringUtils;

public enum JdbcType {
    @Value(2004)
    BLOB,
    @Value(6)
    FLOAT,
    @Value(2003)
    ARRAY,
    @Value(-2)
    BINARY,
    @Value(1)
    CHAR,
    @Value(3)
    DECIMAL,
    @Value(-15)
    NCHAR,
    @Value(-9)
    NVARCHAR,
    @Value(2000)
    JAVA_OBJECT,
    @Value(93)
    TIMESTAMP,
    @Value(8)
    DOUBLE,
    @Value(-6)
    TINYINT,
    @Value(4)
    INTEGER,
    @Value(2011)
    NCLOB,
    @Value(2)
    NUMERIC,
    @Value(2005)
    CLOB,
    @Value(-1)
    LONGVARCHAR,
    @Value(0)
    NULL,
    @Value(-16)
    LONGNVARCHAR,
    @Value(2001)
    DISTINCT,
    @Value(-5)
    BIGINT,
    @Value(92)
    TIME,
    @Value(-7)
    BIT,
    @Value(16)
    BOOLEAN,
    @Value(1111)
    OTHER,
    @Value(91)
    DATE,
    @Value(2006)
    REF,
    @Value(70)
    DATALINK,
    @Value(5)
    SMALLINT,
    @Value(7)
    REAL,
    @Value(12)
    VARCHAR,
    @Value(-3)
    VARBINARY,
    @Value(-4)
    LONGVARBINARY,
    @Value(2002)
    STRUCT;

    public static JdbcType valueOfDb(String value) {
        if (value.contains("(")) {
            value = value.substring(0, value.indexOf('('));
        }
        for (JdbcType type : JdbcType.values()) {
            if (StringUtils.containsIgnoreCase(value, type.name())) {
                return type;
            }
        }
        throw new RuntimeException("The jdbc type is not support.");
    }

    public static JdbcType valueOfDb(int value) {
        try {
            for (JdbcType type : JdbcType.values()) {
                int v = EnumsUtils.getAnnotation(type, Value.class).value();
                if(v == value)
                    return type;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("The jdbc type is not support.");
    }
}
