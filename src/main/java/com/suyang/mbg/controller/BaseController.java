package com.suyang.mbg.controller;

import javafx.event.Event;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BaseController {
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
}
