package com.suyang.mbg.domain;

import com.suyang.mbg.generator.enums.GenType;

import java.io.Serializable;

public class GenSettings implements Serializable {
    private static final long serialVersionUID = -4885022341022401189L;
    private String tablePrefix = "tb_";
    private String entityName = "${EntityName}";
    private String mapperName = "${EntityName}Mapper";
    private String javaOutput = "";
    private String resourceOutput = "";
    private String entityPackage = "com.example.entities";
    private String mapperPackage = "com.example.mapper";
    private GenType genType = GenType.XmlMapper;
    private boolean overwrite = false;

    public GenSettings() {
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
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

    public String getJavaOutput() {
        return javaOutput;
    }

    public void setJavaOutput(String javaOutput) {
        this.javaOutput = javaOutput;
    }

    public String getResourceOutput() {
        return resourceOutput;
    }

    public void setResourceOutput(String resourceOutput) {
        this.resourceOutput = resourceOutput;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public GenType getGenType() {
        return genType;
    }

    public void setGenType(GenType genType) {
        this.genType = genType;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }
}
