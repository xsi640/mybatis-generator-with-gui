package com.suyang.mbg.generator;

import com.suyang.commons.JsonUtils;
import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.domain.GenSettings;
import com.suyang.mbg.domain.XmlMapperGeneratorConfig;
import com.suyang.mbg.generator.enums.GenType;
import com.suyang.mbg.generator.sql.AnnotationSqlGenerator;
import com.suyang.mbg.generator.sql.SqlGenerator;
import freemarker.template.TemplateException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapperGenerator extends BaseGenerator {
    private static final String TEMPLATE_FILE_NAME = "mapper.ftl";

    private SqlGenerator<String> sqlGenerator = new AnnotationSqlGenerator();

    public MapperGenerator(freemarker.template.Configuration configuration) throws IOException {
        super.configuration = configuration;
        template = this.configuration.getTemplate(TEMPLATE_FILE_NAME);
    }

    void process(GeneratorConfig config, GenSettings settings) throws IOException, TemplateException {
        if (settings.getGenType() == GenType.Annotation) {
            XmlMapperGeneratorConfig xmlMapperGeneratorConfig = JsonUtils.parse(JsonUtils.toString(config), XmlMapperGeneratorConfig.class);
            Map<String, String> annoation = new HashMap<>();
            for (Method method : sqlGenerator.getClass().getDeclaredMethods()) {
                if (method.getReturnType().equals(String.class)) {
                    try {
                        annoation.put(method.getName(), (String) method.invoke(sqlGenerator, config));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            xmlMapperGeneratorConfig.setAnnoation(annoation);

            super.process(getPath(config, settings), xmlMapperGeneratorConfig, settings.isOverwrite());
        } else {
            super.process(getPath(config, settings), config, settings.isOverwrite());
        }
    }

    private String getPath(GeneratorConfig config, GenSettings settings) {
        return toPath(settings.getJavaOutput(),
                settings.getMapperPackage(),
                settings.getMapperName().replace("${EntityName}",
                        config.getEntityName()) + ".java");
    }
}
