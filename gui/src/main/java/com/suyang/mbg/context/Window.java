package com.suyang.mbg.context;

import com.suyang.mbg.controller.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Window {
    private Stage stage;
    private BaseController controller;
    private Scene scene;
    private FXMLLoader loader;

    private List<LoadListener> loadListeners = new ArrayList<>();

    public Window() {
    }

    public Window(Stage stage, BaseController controller, Scene scene, FXMLLoader loader) {
        this.stage = stage;
        this.controller = controller;
        this.scene = scene;
        this.loader = loader;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public BaseController getController() {
        return controller;
    }

    public void setController(BaseController controller) {
        this.controller = controller;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public void addLoadLisener(LoadListener loadListener) {
        this.loadListeners.add(loadListener);
    }

    public void removeLoadLisener(LoadListener loadListener) {
        this.loadListeners.remove(loadListener);
    }

    public void clearLoadLisener(){
        this.loadListeners.clear();
    }

    public List<LoadListener> getLoadListeners() {
        return loadListeners;
    }
}
