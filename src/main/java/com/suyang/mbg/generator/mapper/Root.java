package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Root {
    private Mapper mapper;
    private ResultMap resultMap;
    @XmlElement(name="insert")
    private List<Insert> inserts;
    @XmlElement(name="update")
    private List<Update> updates;
    @XmlElement(name="select")
    private List<Select> selects;
    @XmlElement(name="delete")
    private List<Delete> deletes;

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public ResultMap getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    public List<Insert> getInserts() {
        return inserts;
    }

    public void setInserts(List<Insert> inserts) {
        this.inserts = inserts;
    }

    public List<Update> getUpdates() {
        return updates;
    }

    public void setUpdates(List<Update> updates) {
        this.updates = updates;
    }

    public List<Select> getSelects() {
        return selects;
    }

    public void setSelects(List<Select> selects) {
        this.selects = selects;
    }

    public List<Delete> getDeletes() {
        return deletes;
    }

    public void setDeletes(List<Delete> deletes) {
        this.deletes = deletes;
    }
}
