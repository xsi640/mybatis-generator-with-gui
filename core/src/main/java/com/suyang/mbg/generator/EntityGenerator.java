package com.suyang.mbg.generator;

import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.generator.domain.GenSettings;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.*;

public class EntityGenerator extends BaseGenerator {
    private static final String TEMPLATE_FILE_NAME = "entity.ftl";

    public EntityGenerator(Configuration configuration) throws IOException {
        super.configuration = configuration;
        super.template = this.configuration.getTemplate(TEMPLATE_FILE_NAME);
    }

    public void process(GeneratorConfig config, GenSettings settings) throws IOException, TemplateException {
        super.process(getPath(config, settings), config);
    }

    public String getPath(GeneratorConfig config, GenSettings settings) {
        return super.toPath(settings.getJavaOutput(),
                settings.getEntityPackage(),
                settings.getEntityName().replace("${EntityName}",
                        config.getEntityName()) + ".java");
    }
}
