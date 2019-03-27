package com.suyang.mbg.context;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public Stage show(StageType stageType, String title, Location location, Modality modality) throws IOException, NoSuchFieldException {
        Stage result = null;
        if (stages.containsKey(stageType)) {
            result = stages.get(stageType);
            result.requestFocus();
        } else {
            StageDescribe stageDescribe = StageType.valueOf(stageType);
            Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource(stageDescribe.fxml())));
            result = new Stage();
            stages.put(stageType, result);
            result.setTitle(title);
            result.setWidth(stageDescribe.width());
            result.setHeight(stageDescribe.height());
            if (location != null) {
                if (location.getWidth() != -1)
                    result.setWidth(location.getWidth());
                if (location.getHeight() != -1)
                    result.setHeight(location.getHeight());
                if (location.getTop() != -1)
                    result.setY(location.getTop());
                if (location.getLeft() != -1)
                    result.setX(location.getLeft());
            }
            result.initModality(modality);
            result.setScene(scene);
            result.show();
        }
        return result;
    }

    public Stage start(StageType stageType, String title) throws IOException, NoSuchFieldException {
        this.main = this.show(stageType, title, Location.Empty(), Modality.NONE);
        return this.main;
    }
}
