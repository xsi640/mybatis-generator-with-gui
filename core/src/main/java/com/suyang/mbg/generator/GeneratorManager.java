package com.suyang.mbg.generator;

import com.suyang.mbg.domain.Column;
import com.suyang.mbg.domain.DataSourceConfig;
import com.suyang.mbg.domain.Table;
import com.suyang.mbg.enums.DataSourceType;
import com.suyang.mbg.database.factory.DatabaseServiceFactory;
import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.domain.GenSettings;
import com.suyang.mbg.domain.PrimaryKey;
import com.suyang.mbg.domain.Property;
import com.suyang.mbg.generator.enums.GenType;
import com.suyang.mbg.generator.factory.GeneratorFactory;
import com.suyang.commons.NameUtils;
import com.suyang.mbg.logger.Level;
import com.suyang.mbg.logger.Logger;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneratorManager {
    private Logger logger;
    private DataSourceConfig dataSourceConfig;
    private GenSettings genSettings;

    private boolean started;

    public GeneratorManager(Logger logger, DataSourceConfig dataSourceConfig, GenSettings genSettings) {
        this.logger = logger;
        this.dataSourceConfig = dataSourceConfig;
        this.genSettings = genSettings;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public GenSettings getGenSettings() {
        return genSettings;
    }

    public boolean isStarted() {
        return started;
    }

    public void start() {
        if (!started) {
            started = true;
        } else {
            return;
        }

        try {
            beginStart();
        } catch (Exception e) {
            logger.append(e.getMessage(), Level.ERROR);
        } finally {
            started = false;
        }
    }

    private void beginStart() throws IOException, TemplateException {
        appendText("开始生成代码...");
        List<Table> tables = readDatabase();
        if (tables.isEmpty()) {
            appendText("数据库为空...请检查数据库是否存在数据表", Level.WARNING);
            return;
        }
        List<GeneratorConfig> generatorConfigs = analysisDatabase(tables);
        appendText("开始生成实体类...");
        for (GeneratorConfig generatorConfig : generatorConfigs) {
            GeneratorFactory.getInstance().getEntityGenerator().process(generatorConfig, genSettings);
            appendText("生成类 " + this.genSettings.getEntityName().replace("${EntityName}", generatorConfig.getEntityName()));
        }
        appendText("开始生成实体类...完成");

        appendText("开始生成Mpper接口类...");
        for (GeneratorConfig generatorConfig : generatorConfigs) {
            GeneratorFactory.getInstance().getMapperGenerator().process(generatorConfig, genSettings);
            appendText("生成接口 " + this.genSettings.getMapperName().replace("${EntityName}", generatorConfig.getEntityName()));
        }
        appendText("开始生成Mpper接口类...完成");

        if (this.genSettings.getGenType() == GenType.XmlMapper) {
            appendText("开始生成XMl映射文件...");
            for (GeneratorConfig generatorConfig : generatorConfigs) {
                GeneratorFactory.getInstance().getXmlGenGenerator().process(generatorConfig, genSettings);
                appendText("生成XML映射 " + generatorConfig.getEntityName() + "Mapper.xml");
            }
            appendText("开始生成XMl映射文件...完成");
        }
        appendText("完成...");
    }

    private List<Table> readDatabase() {
        appendText("正在读取数据库信息...");
        List<Table> tables = new ArrayList<>();
        try {
            tables = DatabaseServiceFactory.getInstance().getDatabaseService(DataSourceType.MySQL).getTables(this.dataSourceConfig);
            appendText(String.format("一共%s个数据表", String.valueOf(tables.size())));
        } catch (Exception e) {
            appendText("错误：" + e);
        }
        for (Table table : tables) {
            appendText(table.getName());
        }
        appendText("正在读取数据库信息...完成");
        return tables;
    }

    private List<GeneratorConfig> analysisDatabase(List<Table> tables) {
        List<GeneratorConfig> result = new ArrayList<>();
        appendText("正在分析数据库字段...");
        for (Table table : tables) {
            String entityName = table.getName();
            if (entityName.startsWith(this.genSettings.getTablePrefix())) {
                entityName = entityName.substring(this.genSettings.getTablePrefix().length());
            }
            entityName = NameUtils.toPascalName(entityName);
            GeneratorConfig generatorConfig = new GeneratorConfig();
            generatorConfig.setMapperPackage(this.genSettings.getMapperPackage());
            generatorConfig.setEntityPackage(this.genSettings.getEntityPackage());
            generatorConfig.setMapperName(this.genSettings.getMapperName().replace("${EntityName}", entityName));
            generatorConfig.setEntityName(this.genSettings.getEntityName().replace("${EntityName}", entityName));
            generatorConfig.setTableName(table.getName());
            for (Column column : table.getColumns()) {
                if (column.isVirtual())
                    continue;
                if (column.isPrimary()) {
                    PrimaryKey primaryKey = new PrimaryKey(NameUtils.toCamelName(column.getName()), column.getName(), column.getType(), column.isAutoIncrement());
                    generatorConfig.setPrimaryKey(primaryKey);
                } else {
                    Property property = new Property(NameUtils.toCamelName(column.getName()), column.getName(), column.getType());
                    generatorConfig.getProperties().add(property);
                }
            }
            result.add(generatorConfig);
        }
        appendText("正在分析数据库字段...完成");
        return result;
    }

    private void appendText(String message) {
        this.appendText(message, Level.INFO);
    }

    private void appendText(String message, Level level) {
        if (logger != null)
            this.logger.append(message, level);
    }
}
