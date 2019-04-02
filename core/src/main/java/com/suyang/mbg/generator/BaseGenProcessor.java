package com.suyang.mbg.generator;

import com.suyang.mbg.domain.GeneratorConfig;
import freemarker.template.TemplateException;

import java.io.IOException;

public interface BaseGenProcessor {
    void process(String output, GeneratorConfig config, boolean overwrite) throws IOException, TemplateException;
}
