package com.suyang.mbg.controller;

import com.suyang.mbg.context.GenSettings;
import com.suyang.mbg.database.domain.Column;
import com.suyang.mbg.database.domain.DataSourceConfig;
import com.suyang.mbg.database.domain.Table;
import com.suyang.mbg.database.enums.DataSourceType;
import com.suyang.mbg.database.enums.JdbcType;
import com.suyang.mbg.database.factory.DatabaseServiceFactory;
import com.suyang.mbg.enums.JavaType;
import com.suyang.mbg.generator.domain.Entity;
import com.suyang.mbg.generator.domain.PrimaryKey;
import com.suyang.mbg.generator.domain.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class GeneratorController extends BaseController {
    @FXML
    private TextArea txtArea;
    @FXML
    private Button btnClose;

    private DataSourceConfig dataSourceConfig;
    private GenSettings genSettings;
    private List<Table> tables;
    private List<Entity> entities = new ArrayList<>();

    @Override
    public void onLoad() {
        this.btnClose.setOnAction(this::btnCloseAction);
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public GenSettings getGenSettings() {
        return genSettings;
    }

    public void setGenSettings(GenSettings genSettings) {
        this.genSettings = genSettings;
    }

    private void btnCloseAction(ActionEvent event) {
        this.close();
    }

    public void start() {
        appendText("开始生成代码...");
        tables = readDatabase();
        if (tables.isEmpty()) {
            appendText("数据库为空...请检查数据库是否存在数据表", Level.WARNING);
            return;
        }
        analysisDatabase();
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

    private void analysisDatabase() {
        appendText("正在分析数据库字段...");
        for (Table table : tables) {
            String entityName = table.getName();
            if (entityName.startsWith(this.genSettings.getTablePrefix())) {
                entityName = entityName.substring(this.genSettings.getTablePrefix().length());
            }
            Entity entity = new Entity();
            entity.setEntityPackage(this.genSettings.getEntityPackage());
            entity.setClassName(toPascalName(entityName));
            entity.setTableName(table.getName());
            for (Column column : table.getColumns()) {
                if (column.isVirtual())
                    continue;
                if (column.isPrimary()) {
                    PrimaryKey primaryKey = new PrimaryKey(toCamelName(column.getName()), column.getName(), JavaType.valueOfJdbc(column.getType()), column.getType(), column.isAutoIncrement());
                    entity.setPrimaryKey(primaryKey);
                } else {
                    Property property = new Property(toCamelName(column.getName()), column.getName(), JavaType.valueOfJdbc(column.getType()), column.getType());
                    entity.getProperties().add(property);
                }
            }
            entities.add(entity);
        }
        appendText("正在分析数据库字段...完成");
    }

    private String toCamelName(String name) {
        String s = toPascalName(name);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private static String toPascalName(String name) {
        if (name.contains("-") || name.contains("_")) {
            String[] ns = name.split("[-_]");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ns.length; i++) {
                String s = ns[i];
                if (StringUtils.isEmpty(s))
                    continue;
                sb.append(s.substring(0, 1).toUpperCase());
                sb.append(s.substring(1).toLowerCase());
            }
            return sb.toString();
        } else {
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
    }

    private void appendText(String message) {
        this.appendText(message, Level.INFO);
    }

    private void appendText(String message, Level level) {
        txtArea.appendText(level + ":" + message + "\r\n");
    }
}
