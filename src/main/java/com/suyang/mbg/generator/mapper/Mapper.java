package com.suyang.mbg.generator.mapper;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Mapper {
    @XmlAttribute
    private String namespace;
    private Sql sql;
    private ResultMap resultMap;
    @XmlElement(name = "insert")
    private List<Insert> inserts;
    @XmlElement(name = "update")
    private List<Update> updates;
    @XmlElement(name = "select")
    private List<Select> selects;
    @XmlElement(name = "delete")
    private List<Delete> deletes;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Sql getSql() {
        return sql;
    }

    public void setSql(Sql sql) {
        this.sql = sql;
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
