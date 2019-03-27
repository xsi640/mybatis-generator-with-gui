package com.suyang.mbg;

import com.suyang.mbg.context.ApplicationContext;
import com.suyang.mbg.context.StageType;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        ApplicationContext.getInstance().start(StageType.Main, "主窗口");
    }
}
