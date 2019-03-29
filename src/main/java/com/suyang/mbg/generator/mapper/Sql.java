package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sql {
    @XmlAttribute
    private String id = "Base_Column_List";
    @XmlValue
    private String value;

    public Sql(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
