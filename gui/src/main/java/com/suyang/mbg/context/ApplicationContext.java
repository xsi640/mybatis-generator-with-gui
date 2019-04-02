package com.suyang.mbg.context;

import com.suyang.mbg.controller.BaseController;
import com.suyang.mbg.domain.DataSourceConfig;
import com.suyang.commons.IOUtils;
import com.suyang.commons.JsonUtils;
import com.suyang.mbg.domain.GenSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

    private Map<StageType, Window> windows = new HashMap<>();
    private Window main;

    private ObservableList<DataSourceConfig> dataSourceConfigs = FXCollections.observableArrayList();

    private Map<String, GenSettings> genSettingsMap = new HashMap<>();

    public Map<StageType, Window> getWindows() {
        return windows;
    }

    public Window getWindow(StageType stageType) {
        return this.windows.get(stageType);
    }

    public Window getMain() {
        return main;
    }

    public ObservableList<DataSourceConfig> getDataSourceConfigs() {
        return dataSourceConfigs;
    }

    public Window show(StageType stageType, String title, Modality modality) throws IOException, NoSuchFieldException {
        Window result;
        if (windows.containsKey(stageType)) {
            result = windows.get(stageType);
            result.getStage().requestFocus();
        } else {
            StageDescribe stageDescribe = StageType.valueOf(stageType);
            result = new Window();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(stageDescribe.fxml()));
            result.setLoader(loader);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            result.setScene(scene);
            Stage stage = new Stage();
            result.setStage(stage);
            BaseController controller = loader.getController();
            result.setController(controller);
            controller.setWindow(result);
            stage.setOnCloseRequest(event -> windows.remove(stageType));

            windows.put(stageType, result);
            result.getStage().setResizable(stageDescribe.resizable());
            if (StringUtils.isEmpty(title))
                result.getStage().setTitle(title);
            else if (StringUtils.isEmpty(stageDescribe.title()))
                result.getStage().setTitle(stageDescribe.title());
            if (stageDescribe.width() != -1)
                result.getStage().setWidth(stageDescribe.width());
            if (stageDescribe.height() != -1)
                result.getStage().setHeight(stageDescribe.height());
            if (stageDescribe.minWidth() != -1)
                result.getStage().setMinWidth(stageDescribe.minWidth());
            if (stageDescribe.minHeight() != -1)
                result.getStage().setMinHeight(stageDescribe.minHeight());
            result.getStage().initModality(modality);
            result.getStage().setScene(scene);
            result.getStage().show();
        }
        return result;
    }

    public Window start() throws IOException, NoSuchFieldException {
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

    public void removeDataSourceConfig(DataSourceConfig config) {
        this.genSettingsMap.remove(config.getName());
        this.dataSourceConfigs.remove(config);
        this.saveGenSettings();
    }
}
