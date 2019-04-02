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
            annoation.put("insert", sqlGenerator.insert(config));
            annoation.put("insertCollection", sqlGenerator.insertCollection(config));
            annoation.put("insertOrUpdate", sqlGenerator.insertOrUpdate(config));
            annoation.put("update", sqlGenerator.update(config));
            annoation.put("delete", sqlGenerator.delete(config));
            annoation.put("deleteAll", sqlGenerator.deleteAll(config));
            annoation.put("deletes", sqlGenerator.deletes(config));
            annoation.put("findAll", sqlGenerator.findAll(config));
            annoation.put("findById", sqlGenerator.findById(config));
            annoation.put("count", sqlGenerator.count(config));
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
