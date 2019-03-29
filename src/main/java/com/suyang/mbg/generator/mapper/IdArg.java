package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IdArg extends Arg {

    public IdArg() {
    }

    public IdArg(String column, String javaType, String jdbcType) {
        super(column, javaType, jdbcType);
    }
}
