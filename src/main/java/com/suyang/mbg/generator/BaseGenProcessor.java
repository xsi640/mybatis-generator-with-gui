package com.suyang.mbg.generator;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface BaseGenProcessor {
    void process(String output, BaseGenConfig config) throws IOException, TemplateException;
}
