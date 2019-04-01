package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Id extends Result {
    public Id() {
    }

    public Id(String property, String column, String javaType, String jdbcType) {
        super(property, column, javaType, jdbcType);
    }
}
