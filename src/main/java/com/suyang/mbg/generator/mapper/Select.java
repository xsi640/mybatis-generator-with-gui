package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Select {
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String parameterType;
    @XmlAttribute
    private String resultMap;
    @XmlAttribute
    private String resultType;
    @XmlValue
    private String value;

    public Select() {
    }

    public Select(String id, String parameterType) {
        this.id = id;
        this.parameterType = parameterType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultMap() {
        return resultMap;
    }

    public void setResultMap(String resultMap) {
        this.resultMap = resultMap;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
