package com.suyang.mbg.generator;

import com.suyang.mbg.enums.JavaType;

public class MapperGenConfig extends BaseGenConfig {
    private String mapperNamespace;
    private String entityName;
    private String suffix;
    private JavaType primaryKeyType;

    public MapperGenConfig() {
    }

    public String getMapperNamespace() {
        return mapperNamespace;
    }

    public void setMapperNamespace(String mapperNamespace) {
        this.mapperNamespace = mapperNamespace;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public JavaType getPrimaryKeyType() {
        return primaryKeyType;
    }

    public void setPrimaryKeyType(JavaType primaryKeyType) {
        this.primaryKeyType = primaryKeyType;
    }
}
