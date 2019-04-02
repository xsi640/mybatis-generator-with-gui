package com.suyang.mbg.generator;

import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.generator.domain.GenSettings;
import freemarker.template.TemplateException;

import java.io.*;

public class MapperGenerator extends BaseGenerator {
    private static final String TEMPLATE_FILE_NAME = "mapper.ftl";

    public MapperGenerator(freemarker.template.Configuration configuration) throws IOException {
        super.configuration = configuration;
        template = this.configuration.getTemplate(TEMPLATE_FILE_NAME);
    }

    public void process(GeneratorConfig config, GenSettings settings) throws IOException, TemplateException {
        super.process(getPath(config, settings), config);
    }

    public String getPath(GeneratorConfig config, GenSettings settings) {
        return toPath(settings.getJavaOutput(),
                settings.getMapperPackage(),
                settings.getMapperName().replace("${EntityName}",
                        config.getEntityName()) + ".java");
    }
}
