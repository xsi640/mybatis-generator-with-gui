package com.suyang.mbg.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

public abstract class BaseGenerator implements BaseGenProcessor {
    protected Configuration configuration;
    protected Template template;

    public Template getTemplate() {
        return template;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void process(String output, BaseGenConfig config) {
        File file = new File(output);
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            template.process(config, new OutputStreamWriter(fileOutputStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public void process(OutputStreamWriter writer, BaseGenConfig config) {
        try {
            template.process(config, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
