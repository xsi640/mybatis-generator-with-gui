package com.suyang.mbg.generator.domain;

import java.util.ArrayList;
import java.util.List;

public class GeneratorSettings {
    private String basePackage;
    private String output;
    private String entityNamespace;
    private String mapperNamespace;

    private List<Entity> entities = new ArrayList<>();

    public GeneratorSettings() {
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getEntityNamespace() {
        return entityNamespace;
    }

    public void setEntityNamespace(String entityNamespace) {
        this.entityNamespace = entityNamespace;
    }

    public String getMapperNamespace() {
        return mapperNamespace;
    }

    public void setMapperNamespace(String mapperNamespace) {
        this.mapperNamespace = mapperNamespace;
    }
}
