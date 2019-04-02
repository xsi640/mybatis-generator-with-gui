package com.suyang.mbg.generator;

import com.suyang.commons.IOUtils;
import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.generator.domain.GenSettings;
import com.suyang.mbg.generator.xml.XmlConverter;
import com.suyang.mbg.utils.SerializeUtils;
import freemarker.template.TemplateException;

import java.io.IOException;

public class XmlGenGenerator implements BaseGenProcessor {

    @Override
    public void process(String output, GeneratorConfig config) throws IOException, TemplateException {
        SerializeUtils.xmlSerializeByJAXBToFile(output, XmlConverter.getInstance().convert(config));
    }

    public void process(GeneratorConfig config, GenSettings settings) throws IOException, TemplateException {
        this.process(getPath(config, settings), config);
    }

    public String getPath(GeneratorConfig config, GenSettings settings) {
        return IOUtils.combine(settings.getResourceOutput(),
                config.getEntityName() + "Mapper.xml");
    }
}
