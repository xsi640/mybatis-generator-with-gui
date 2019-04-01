package com.suyang.mbg.generator.factory;

import com.suyang.mbg.generator.EntityGenerator;
import com.suyang.mbg.generator.MapperGenerator;
import com.suyang.mbg.generator.XmlGenGenerator;
import freemarker.template.Configuration;

import java.io.IOException;

public class GenFactory {
    private static GenFactory instance = new GenFactory();

    public static GenFactory getInstance() {
        return instance;
    }

    private Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassForTemplateLoading(this.getClass(), "/template");
        cfg.setDefaultEncoding("utf-8");
        return cfg;
    }

    public EntityGenerator getEntityGenerator() throws IOException {
        return new EntityGenerator(getConfiguration());
    }

    public MapperGenerator getMapperGenerator() throws IOException {
        return new MapperGenerator(getConfiguration());
    }

    public XmlGenGenerator getXmlGenGenerator() {
        return new XmlGenGenerator();
    }
}
