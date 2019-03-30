package com.suyang.mbg.controller;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {
    private Stage currentStage;

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void onLoad() {
    }

    public void close() {
        Event.fireEvent(this.getCurrentStage(), new WindowEvent(this.getCurrentStage(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.onLoad();
    }
}
