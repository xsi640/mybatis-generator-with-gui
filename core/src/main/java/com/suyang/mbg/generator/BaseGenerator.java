package com.suyang.mbg.generator;

import com.suyang.commons.IOUtils;
import com.suyang.mbg.domain.GeneratorConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseGenerator implements BaseGenProcessor {
    protected Configuration configuration;
    protected Template template;

    public Template getTemplate() {
        return template;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void process(String output, GeneratorConfig config) throws IOException, TemplateException {
        File file = new File(output);
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            template.process(config, new OutputStreamWriter(fileOutputStream));
        } catch (Exception e) {
            throw e;
        }
    }

    public String toPath(String path, String packageName, String fileName) {
        List<String> paths = new ArrayList<>();
        paths.add(path);
        for (String s : packageName.split("\\.")) {
            paths.add(s);
        }
        paths.add(fileName);

        return IOUtils.combine(paths.toArray(new String[paths.size()]));
    }
}
