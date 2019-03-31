package com.suyang.mbg.generator;

import com.suyang.mbg.enums.JavaType;

public class MapperGenConfig extends BaseGenConfig {
    private String mapperPackage;
    private String entityPackage;
    private String entityName;
    private String mapperName;
    private JavaType primaryKeyType;

    public MapperGenConfig() {
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public JavaType getPrimaryKeyType() {
        return primaryKeyType;
    }

    public void setPrimaryKeyType(JavaType primaryKeyType) {
        this.primaryKeyType = primaryKeyType;
    }
}
