package com.suyang.mbg.generator.domain;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    private String entityPackage;
    private Property primaryKey = null;
    private List<Property> properties = new ArrayList<>();

    private String className;
    private String tableName;

    public Entity() {
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public Property getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Property primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
