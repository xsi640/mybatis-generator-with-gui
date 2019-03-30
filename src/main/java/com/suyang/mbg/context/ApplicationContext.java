package com.suyang.mbg.context;

import com.suyang.mbg.controller.BaseController;
import com.suyang.mbg.database.domain.DataSourceConfig;
import com.suyang.mbg.utils.IOUtils;
import com.suyang.mbg.utils.JsonUtils;
import com.suyang.mbg.utils.SerializeUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContext {

    private static ApplicationContext instance = new ApplicationContext();
    private static final String STORAGE_FILE = ".mybatis_gen/storage";

    public static ApplicationContext getInstance() {
        return instance;
    }

    private Map<StageType, Stage> stages = new HashMap<>();
    private Stage main;

    private ObservableList<DataSourceConfig> dataSourceConfigs = FXCollections.observableArrayList();

    private Map<String, GenSettings> genSettingsMap = new HashMap<>();

    public Map<StageType, Stage> getStages() {
        return stages;
    }

    public Stage getMain() {
        return main;
    }

    public ObservableList<DataSourceConfig> getDataSourceConfigs() {
        return dataSourceConfigs;
    }

    public Stage show(StageType stageType, String title, Modality modality) throws IOException, NoSuchFieldException {
        Stage result;
        if (stages.containsKey(stageType)) {
            result = stages.get(stageType);
            result.requestFocus();
        } else {
            StageDescribe stageDescribe = StageType.valueOf(stageType);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(stageDescribe.fxml()));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            result = new Stage();
            BaseController controller = loader.getController();
            controller.setCurrentStage(result);
            controller.onLoad();
            result.setOnCloseRequest(event -> {
                stages.remove(stageType);
            });
            stages.put(stageType, result);
            result.setResizable(stageDescribe.resizable());
            if (StringUtils.isEmpty(title))
                result.setTitle(title);
            else if (StringUtils.isEmpty(stageDescribe.title()))
                result.setTitle(stageDescribe.title());
            if (stageDescribe.width() != -1)
                result.setWidth(stageDescribe.width());
            if (stageDescribe.height() != -1)
                result.setHeight(stageDescribe.height());
            if (stageDescribe.minWidth() != -1)
                result.setMinWidth(stageDescribe.minWidth());
            if (stageDescribe.minHeight() != -1)
                result.setMinHeight(stageDescribe.minHeight());
            result.initModality(modality);
            result.setScene(scene);
            result.show();
        }
        return result;
    }

    public Stage start() throws IOException, NoSuchFieldException {
        this.main = this.show(StageType.Main, null, Modality.NONE);
        this.loadGenSettings();
        return this.main;
    }

    public void loadGenSettings() {
        String path = IOUtils.combine(System.getProperty("user.home"), STORAGE_FILE);
        if (!IOUtils.exists(path))
            return;
        Storage storage = JsonUtils.parse(IOUtils.readFileAllText(path), Storage.class);
        if (storage.getConfigs() != null && !storage.getConfigs().isEmpty()) {
            storage.getConfigs().forEach(c -> this.dataSourceConfigs.add(c));
        }
        if (storage.getSettings() != null && !storage.getSettings().isEmpty()) {
            storage.getSettings().forEach((k, v) -> this.genSettingsMap.put(k, v));
        }
    }

    public void saveGenSettings() {
        List<DataSourceConfig> configs = new ArrayList<>();
        this.dataSourceConfigs.forEach(c -> configs.add(c));
        String path = IOUtils.combine(System.getProperty("user.home"), STORAGE_FILE);
        IOUtils.writeFileAllText(path, JsonUtils.toString(new Storage(configs, this.genSettingsMap)), true);
    }

    public GenSettings getGenSettings(String configName) {
        return this.genSettingsMap.get(configName);
    }

    public void addDataSourceConfig(DataSourceConfig config) {
        if (!this.dataSourceConfigs.contains(config))
            this.dataSourceConfigs.add(config);
        this.genSettingsMap.put(config.getName(), new GenSettings());
        this.saveGenSettings();
    }
}
