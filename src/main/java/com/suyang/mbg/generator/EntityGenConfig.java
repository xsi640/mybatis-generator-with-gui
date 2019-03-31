package com.suyang.mbg.generator;

import com.suyang.mbg.generator.domain.Property;

import java.util.ArrayList;
import java.util.List;

public class EntityGenConfig extends BaseGenConfig {
    private String entityPackage;

    private Property primaryKey = null;
    private List<Property> properties = new ArrayList<>();
    private String entityName;

    public EntityGenConfig() {
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

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
