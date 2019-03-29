package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Insert {
    @XmlAttribute
    private String id = "insert";
    @XmlAttribute
    private String keyProperty;
    @XmlAttribute
    private String parameterType;
    @XmlAttribute
    private boolean useGeneratedKeys = false;

    private String value;

    public Insert() {
    }

    public Insert(String id, String keyProperty, String parameterType, boolean useGeneratedKeys, String value) {
        this.id = id;
        this.keyProperty = keyProperty;
        this.parameterType = parameterType;
        this.useGeneratedKeys = useGeneratedKeys;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyProperty() {
        return keyProperty;
    }

    public void setKeyProperty(String keyProperty) {
        this.keyProperty = keyProperty;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public boolean isUseGeneratedKeys() {
        return useGeneratedKeys;
    }

    public void setUseGeneratedKeys(boolean useGeneratedKeys) {
        this.useGeneratedKeys = useGeneratedKeys;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
