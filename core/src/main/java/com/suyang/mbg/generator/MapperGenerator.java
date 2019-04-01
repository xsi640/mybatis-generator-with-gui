package com.suyang.mbg.generator;

import java.io.*;

public class MapperGenerator extends BaseGenerator {
    private static final String TEMPLATE_FILE_NAME = "mapper.ftl";

    public MapperGenerator(freemarker.template.Configuration configuration) throws IOException {
        super.configuration = configuration;
        template = this.configuration.getTemplate(TEMPLATE_FILE_NAME);
    }
}
