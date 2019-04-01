package com.suyang.mbg.generator;

import com.suyang.mbg.context.GenSettings;
import com.suyang.mbg.database.domain.Column;
import com.suyang.mbg.database.domain.DataSourceConfig;
import com.suyang.mbg.database.domain.Table;
import com.suyang.mbg.database.enums.DataSourceType;
import com.suyang.mbg.database.factory.DatabaseServiceFactory;
import com.suyang.mbg.generator.domain.Entity;
import com.suyang.mbg.generator.domain.PrimaryKey;
import com.suyang.mbg.generator.domain.Property;
import com.suyang.mbg.generator.factory.GenFactory;
import com.suyang.mbg.utils.IOUtils;
import com.suyang.mbg.utils.NameUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
        } catch (IOException e) {
            logger.append(e.getMessage(), Level.OFF);
        }
    }

    private void beginStart() throws IOException {
        appendText("开始生成代码...");
        List<Table> tables = readDatabase();
        if (tables.isEmpty()) {
            appendText("数据库为空...请检查数据库是否存在数据表", Level.WARNING);
            return;
        }
        List<Entity> entities = analysisDatabase(tables);
        appendText("开始生成实体类...");
        for (Entity entity : entities) {
            GenFactory.getInstance().getEntityGenerator().process(
                    toPath(this.genSettings.getJavaOutput(), this.genSettings.getEntityPackage(), this.genSettings.getEntityName().replace("${EntityName}", entity.getClassName()) + ".java"),
                    getEntityGenConfig(entity));
            appendText("生成类 " + this.genSettings.getEntityName().replace("${EntityName}", entity.getClassName()));
        }
        appendText("开始生成实体类...完成");

        appendText("开始生成Mpper接口类...");
        for (Entity entity : entities) {
            GenFactory.getInstance().getMapperGenerator().process(
                    toPath(this.genSettings.getJavaOutput(), this.genSettings.getMapperPackage(), this.genSettings.getMapperName().replace("${EntityName}", entity.getClassName()) + ".java"),
                    getMapperGenConfig(entity));
            appendText("生成接口 " + this.genSettings.getMapperName().replace("${EntityName}", entity.getClassName()));
        }
        appendText("开始生成Mpper接口类...完成");

        appendText("开始生成XMl映射文件...");
        for (Entity entity : entities) {
            GenFactory.getInstance().getXmlGenGenerator().process(IOUtils.combine(this.genSettings.getResourceOutput(), entity.getClassName() + "Mapper.xml"), getXmlGenConfig(entity));
            appendText("生成XML映射 " + entity.getClassName() + "Mapper.xml");
        }
        appendText("开始生成XMl映射文件...完成");
        appendText("完成...");
    }

    private XmlGenConfig getXmlGenConfig(Entity entity) {
        XmlGenConfig config = new XmlGenConfig();
        config.setTableName(entity.getTableName());
        config.setEntityPackage(this.genSettings.getEntityPackage());
        config.setMapperPackage(this.genSettings.getMapperPackage());
        config.setEntityName(this.genSettings.getEntityName().replace("${EntityName}", entity.getClassName()));
        config.setMapperName(this.genSettings.getMapperName().replace("${EntityName}", entity.getClassName()));
        config.setPrimaryKey(entity.getPrimaryKey());
        config.setProperties(entity.getProperties());
        return config;
    }

    private BaseGenConfig getMapperGenConfig(Entity entity) {
        MapperGenConfig config = new MapperGenConfig();
        config.setMapperPackage(this.genSettings.getMapperPackage());
        config.setEntityPackage(this.genSettings.getEntityPackage());
        config.setEntityName(this.genSettings.getEntityName().replace("${EntityName}", entity.getClassName()));
        config.setMapperName(this.genSettings.getMapperName().replace("${EntityName}", entity.getClassName()));
        config.setPrimaryKeyType(entity.getPrimaryKey().getType());
        return config;
    }

    private EntityGenConfig getEntityGenConfig(Entity entity) {
        EntityGenConfig config = new EntityGenConfig();
        config.setEntityPackage(this.genSettings.getEntityPackage());
        config.setPrimaryKey(entity.getPrimaryKey());
        config.setProperties(entity.getProperties());
        config.setEntityName(this.genSettings.getEntityName().replace("${EntityName}", entity.getClassName()));
        return config;
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

    private List<Entity> analysisDatabase(List<Table> tables) {
        List<Entity> result = new ArrayList<>();
        appendText("正在分析数据库字段...");
        for (Table table : tables) {
            String entityName = table.getName();
            if (entityName.startsWith(this.genSettings.getTablePrefix())) {
                entityName = entityName.substring(this.genSettings.getTablePrefix().length());
            }
            Entity entity = new Entity();
            entity.setEntityPackage(this.genSettings.getEntityPackage());
            entity.setClassName(NameUtils.toPascalName(entityName));
            entity.setTableName(table.getName());
            for (Column column : table.getColumns()) {
                if (column.isVirtual())
                    continue;
                if (column.isPrimary()) {
                    PrimaryKey primaryKey = new PrimaryKey(NameUtils.toCamelName(column.getName()), column.getName(), column.getType(), column.isAutoIncrement());
                    entity.setPrimaryKey(primaryKey);
                } else {
                    Property property = new Property(NameUtils.toCamelName(column.getName()), column.getName(), column.getType());
                    entity.getProperties().add(property);
                }
            }
            result.add(entity);
        }
        appendText("正在分析数据库字段...完成");
        return result;
    }


    public static String toPath(String path, String packageName, String fileName) {
        List<String> paths = new ArrayList<>();
        paths.add(path);
        for (String s : packageName.split("\\.")) {
            paths.add(s);
        }
        paths.add(fileName);

        return IOUtils.combine(paths.toArray(new String[paths.size()]));
    }

    private void appendText(String message) {
        this.appendText(message, Level.INFO);
    }

    private void appendText(String message, Level level) {
        if (logger != null)
            this.logger.append(message, level);
    }
}
