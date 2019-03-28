package com.suyang.mbg.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;

public class EntityGenerator extends BaseGenerator {
    private static final String TEMPLATE_FILE_NAME = "entity.ftl";

    public EntityGenerator(Configuration configuration) throws IOException {
        super.configuration = configuration;
        super.template = this.configuration.getTemplate(TEMPLATE_FILE_NAME);
    }
}
