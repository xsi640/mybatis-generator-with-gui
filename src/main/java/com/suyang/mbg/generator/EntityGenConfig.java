package com.suyang.mbg.generator;

import com.suyang.mbg.domain.Property;

import java.util.ArrayList;
import java.util.List;

public class EntityGenConfig extends BaseGenConfig {
    private String entityNamespace;

    private Property primaryKey = null;
    private List<Property> properties = new ArrayList<>();
    private String className;

    public EntityGenConfig() {
    }

    public String getEntityNamespace() {
        return entityNamespace;
    }

    public void setEntityNamespace(String entityNamespace) {
        this.entityNamespace = entityNamespace;
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
}
