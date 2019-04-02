package com.suyang.mbg.generator;

import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.generator.domain.GenSettings;
import freemarker.template.TemplateException;

import java.io.IOException;

public interface BaseGenProcessor {
    void process(String output, GeneratorConfig config) throws IOException, TemplateException;
}
