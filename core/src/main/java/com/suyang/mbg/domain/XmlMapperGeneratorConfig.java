package com.suyang.mbg.domain;

import java.util.Map;

public class XmlMapperGeneratorConfig extends GeneratorConfig {
    private Map<String, String> annoation = null;

    public Map<String, String> getAnnoation() {
        return annoation;
    }

    public void setAnnoation(Map<String, String> annoation) {
        this.annoation = annoation;
    }
}
