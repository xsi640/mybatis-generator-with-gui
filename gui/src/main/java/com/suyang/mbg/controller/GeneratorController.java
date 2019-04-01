package com.suyang.mbg.controller;

import com.suyang.mbg.generator.domain.GenSettings;
import com.suyang.mbg.database.domain.DataSourceConfig;
import com.suyang.mbg.logger.Level;
import com.suyang.mbg.generator.GeneratorManager;
import com.suyang.mbg.logger.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class GeneratorController extends BaseController implements Logger {
    @FXML
    private TextArea txtArea;
    @FXML
    private Button btnClose;

    private DataSourceConfig dataSourceConfig;
    private GenSettings genSettings;
    private GeneratorManager generatorManager;

    private Thread thread;

    @Override
    public void onLoad() {
        this.btnClose.setOnAction(this::btnCloseAction);
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public GenSettings getGenSettings() {
        return genSettings;
    }

    public void setGenSettings(GenSettings genSettings) {
        this.genSettings = genSettings;
    }

    private void btnCloseAction(ActionEvent event) {
        this.close();
    }

    public void start() {
        this.generatorManager = new GeneratorManager(this, this.dataSourceConfig, this.genSettings);
        thread = new Thread(() -> this.generatorManager.start());
        thread.start();
    }

    @Override
    public void append(String message, Level level) {
        Platform.runLater(() -> {
            txtArea.appendText(level + ":" + message + "\r\n");
        });
    }
}
