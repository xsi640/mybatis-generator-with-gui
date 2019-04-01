package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.*;

@XmlSeeAlso({Id.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Result {
    @XmlAttribute
    private String property;
    @XmlAttribute
    private String column;
    @XmlAttribute
    private String javaType;
    @XmlAttribute
    private String jdbcType;

    public Result() {
    }

    public Result(String property, String column, String javaType, String jdbcType) {
        this.property = property;
        this.column = column;
        this.javaType = javaType;
        this.jdbcType = jdbcType;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
