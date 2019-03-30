package com.suyang.mbg.database.enums;

public enum JdbcType {
    VARCHAR,
    CHAR,
    BLOB,
    TEXT,
    INTEGER,
    TINYINT,
    SMALLINT,
    MEDIUMINT,
    BIT,
    BIGINT,
    FLOAT,
    DOUBLE,
    DECIMAL,
    BOOLEAN,
    DATE,
    DATETIME,
    TIMESTAMP,
    ;

    public static JdbcType valueOfDb(String value){
        return JdbcType.INTEGER;
    }
}
