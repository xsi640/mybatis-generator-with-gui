package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.*;

@XmlSeeAlso({IdArg.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Arg {
    @XmlAttribute
    private String column;
    @XmlAttribute
    private String javaType;
    @XmlAttribute
    private String jdbcType;

    public Arg() {
    }

    public Arg(String column, String javaType, String jdbcType) {
        this.column = column;
        this.javaType = javaType;
        this.jdbcType = jdbcType;
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
