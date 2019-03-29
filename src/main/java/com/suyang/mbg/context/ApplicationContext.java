package com.suyang.mbg.context;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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

    public Stage show(StageType stageType, String title, Modality modality) throws IOException, NoSuchFieldException {
        Stage result;
        if (stages.containsKey(stageType)) {
            result = stages.get(stageType);
            result.requestFocus();
        } else {
            StageDescribe stageDescribe = StageType.valueOf(stageType);
            Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource(stageDescribe.fxml())));
            result = new Stage();
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
