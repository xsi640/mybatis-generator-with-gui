package com.suyang.mbg.controller;

import com.suyang.mbg.context.Window;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {
    private Window currentWindow;

    public Window getWindow() {
        return currentWindow;
    }

    public void setWindow(Window currentStage) {
        this.currentWindow = currentStage;
    }

    public void onLoad() {
    }

    public void close() {
        Event.fireEvent(this.currentWindow.getStage(), new WindowEvent(this.currentWindow.getStage(), WindowEvent.WINDOW_CLOSE_REQUEST));
        this.currentWindow.clearLoadLisener();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.onLoad();
        new Thread(() -> {
            while (this.currentWindow == null) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.currentWindow.getLoadListeners().forEach(c -> c.load(currentWindow));
        }).start();
    }
}
