package com.suyang.mbg.generator;

import com.suyang.commons.IOUtils;
import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.domain.GenSettings;
import com.suyang.mbg.generator.xml.XmlGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

public class XmlGenGenerator implements BaseGenProcessor {

    private XmlGenerator xmlGenerator = new XmlGenerator();

    @Override
    public void process(String output, GeneratorConfig config, boolean overwrite) throws IOException, TemplateException {
        IOUtils.writeFileAllText(output, xmlGenerator.convert(config).getFormattedContent(), overwrite);
    }

    void process(GeneratorConfig config, GenSettings settings) throws IOException, TemplateException {
        this.process(getPath(config, settings), config, settings.isOverwrite());
    }

    private String getPath(GeneratorConfig config, GenSettings settings) {
        return IOUtils.combine(settings.getResourceOutput(),
                config.getEntityName() + "Mapper.xml");
    }
}
