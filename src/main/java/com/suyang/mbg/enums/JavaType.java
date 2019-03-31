package com.suyang.mbg.enums;

import com.suyang.mbg.database.enums.JdbcType;

public enum JavaType {
    Integer,
    Float,
    Byte,
    Long,
    ByteArray,
    Object,
    String,
    Boolean,
    Double,
    Date,
    Short;

    public static JavaType valueOfJdbc(JdbcType type) {
        switch (type) {
            case BIT:
            case BOOLEAN:
                return JavaType.Boolean;
            case TINYINT:
                return JavaType.Byte;
            case BLOB:
            case BINARY:
            case VARBINARY:
            case LONGVARBINARY:
                return JavaType.ByteArray;
            case TIMESTAMP:
            case TIME:
            case DATE:
                return JavaType.Date;
            case FLOAT:
            case DOUBLE:
                return JavaType.Double;
            case REAL:
                return JavaType.Float;
            case INTEGER:
                return JavaType.Integer;
            case BIGINT:
                return JavaType.Long;
            case ARRAY:
            case JAVA_OBJECT:
            case NULL:
            case DISTINCT:
            case OTHER:
            case REF:
            case DATALINK:
            case STRUCT:
                return JavaType.Object;
            case SMALLINT:
                return JavaType.Short;
            case CHAR:
            case NCHAR:
            case NVARCHAR:
            case NCLOB:
            case CLOB:
            case LONGVARCHAR:
            case LONGNVARCHAR:
            case VARCHAR:
                return JavaType.String;
        }
        throw new RuntimeException("The jdbc type is not support.");
    }
}
