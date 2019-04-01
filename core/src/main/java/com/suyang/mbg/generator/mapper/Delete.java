package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Delete {
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String parameterType;
    @XmlValue
    private String value;

    public Delete(String id, String parameterType) {
        this.id = id;
        this.parameterType = parameterType;
    }

    public Delete() {
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
