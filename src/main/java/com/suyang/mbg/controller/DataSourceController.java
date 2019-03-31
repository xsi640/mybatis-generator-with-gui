package com.suyang.mbg.controller;

import com.suyang.mbg.Application;
import com.suyang.mbg.context.ApplicationContext;
import com.suyang.mbg.database.domain.DataSourceConfig;
import com.suyang.mbg.database.enums.DataSourceType;
import com.suyang.mbg.database.factory.DatabaseServiceFactory;
import com.suyang.mbg.database.service.DatabaseService;
import com.suyang.mbg.utils.CollectionUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.apache.commons.lang3.StringUtils;

public class DataSourceController extends BaseController {

    private DataSourceType defaultDataSourceType = DataSourceType.MySQL;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtHost;
    @FXML
    private TextField txtPort;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwd;
    @FXML
    private TextField txtDbName;
    @FXML
    private Label txtTest;
    @FXML
    private Button btnTest;

    private DataSourceConfig dataSourceConfig;

    public DataSourceController() {
    }

    @FXML
    public void btnCancelAction(ActionEvent event) {
        super.close();
    }

    @FXML
    public void btnTestAction(ActionEvent event) {
        if (!validate())
            return;
        this.check();
    }

    @FXML
    public void btnSaveAction(ActionEvent event) {
        if (!validate())
            return;
        DataSourceConfig config = getDataSourceConfig();
        saveConfig(config);
        this.close();
    }

    public void setDataSourceConfig(DataSourceConfig config) {
        this.dataSourceConfig = config;
        txtName.setText(this.dataSourceConfig.getName());
        txtHost.setText(this.dataSourceConfig.getHost());
        txtPort.setText(String.valueOf(this.dataSourceConfig.getPort()));
        txtDbName.setText(this.dataSourceConfig.getDbName());
        txtUsername.setText(this.dataSourceConfig.getUsername());
        pwd.setText(this.dataSourceConfig.getPassword());
    }

    private boolean validate() {
        if (StringUtils.isEmpty(txtName.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "数据源名称不能为空！", ButtonType.OK).showAndWait();
            return false;
        }
        if (this.dataSourceConfig == null && CollectionUtils.findOne(ApplicationContext.getInstance().getDataSourceConfigs(), "name", txtName.getText().trim()) != null) {
            new Alert(Alert.AlertType.ERROR, "数据源名称已存在！", ButtonType.OK).showAndWait();
            return false;
        }
        if (StringUtils.isEmpty(txtHost.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "数据库地址不能为空！", ButtonType.OK).showAndWait();
            return false;
        }
        if (StringUtils.isEmpty(txtPort.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "数据库端口号不能为空！", ButtonType.OK).showAndWait();
            return false;
        }
        try {
            int port = Integer.valueOf(txtPort.getText().trim());
            if (port < 1 || port > 65535) {
                new Alert(Alert.AlertType.ERROR, "数据库端口号必须为1~65535！", ButtonType.OK).showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "数据库端口号必须为数字！", ButtonType.OK).showAndWait();
            return false;
        }
        if (StringUtils.isEmpty(txtDbName.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "数据库名称不能为空！", ButtonType.OK).showAndWait();
            return false;
        }
        return true;
    }

    private void saveConfig(DataSourceConfig config) {
        if (this.dataSourceConfig == null) {
            ApplicationContext.getInstance().addDataSourceConfig(config);
        } else {
            this.dataSourceConfig.setName(config.getName());
            this.dataSourceConfig.setHost(config.getHost());
            this.dataSourceConfig.setPort(config.getPort());
            this.dataSourceConfig.setDbName(config.getDbName());
            this.dataSourceConfig.setUsername(config.getUsername());
            this.dataSourceConfig.setPassword(config.getPassword());

            ((MainController) ApplicationContext.getInstance().getMain().getController()).refreshListView();
            ApplicationContext.getInstance().saveGenSettings();
        }
    }

    private void check() {
        this.btnTest.setDisable(true);
        this.txtTest.setVisible(false);
        DataSourceConfig config = getDataSourceConfig();
        if (getDatabaseService().check(config)) {
            this.txtTest.setText("测试通过！");
            this.txtTest.setTextFill(Color.web("#7FFFD4"));
            this.txtTest.setVisible(true);
            this.btnTest.setDisable(false);
        } else {
            this.txtTest.setText("测试失败！");
            this.txtTest.setTextFill(Color.web("#8B008B"));
            this.txtTest.setVisible(true);
            this.btnTest.setDisable(false);
        }
    }

    private DataSourceConfig getDataSourceConfig() {
        String name = txtName.getText().trim();
        String host = txtHost.getText().trim();
        int port = Integer.valueOf(txtPort.getText().trim());
        String username = txtUsername.getText().trim();
        String pasword = pwd.getText().trim();
        String dbName = txtDbName.getText().trim();

        DataSourceConfig config = new DataSourceConfig();
        config.setName(name);
        config.setHost(host);
        config.setPort(port);
        config.setDbName(dbName);
        config.setUsername(username);
        config.setPassword(pasword);
        return config;
    }

    private DatabaseService getDatabaseService() {
        return DatabaseServiceFactory.getInstance().getDatabaseService(defaultDataSourceType);
    }

    @Override
    public void onLoad() {

    }
}
