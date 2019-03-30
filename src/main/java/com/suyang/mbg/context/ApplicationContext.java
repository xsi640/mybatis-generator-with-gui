package com.suyang.mbg.context;

import com.suyang.mbg.controller.BaseController;
import com.suyang.mbg.database.domain.DataSourceConfig;
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
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static ApplicationContext instance = new ApplicationContext();

    public static ApplicationContext getInstance() {
        return instance;
    }

    private Map<StageType, Stage> stages = new HashMap<>();
    private Stage main;

    private ObservableList<DataSourceConfig> dataSourceConfigs = FXCollections.observableArrayList();

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
        return this.main;
    }
}
