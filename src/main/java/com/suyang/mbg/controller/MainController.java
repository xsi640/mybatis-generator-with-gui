package com.suyang.mbg.controller;

import com.suyang.mbg.context.ApplicationContext;
import com.suyang.mbg.context.GenSettings;
import com.suyang.mbg.context.StageType;
import com.suyang.mbg.context.Window;
import com.suyang.mbg.database.domain.DataSourceConfig;
import com.suyang.mbg.enums.GenType;
import com.suyang.mbg.utils.CollectionUtils;
import com.suyang.mbg.utils.Description;
import com.suyang.mbg.utils.EnumsUtils;
import com.suyang.mbg.utils.NameValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

public class MainController extends BaseController {

    private ObservableList<NameValue<GenType>> genTypes = FXCollections.observableArrayList();

    @FXML
    private ListView listView;
    @FXML
    private Button btnAddSource;
    @FXML
    private Button btnRemoveSource;
    @FXML
    private TextField txtEntityName;
    @FXML
    private TextField txtJava;
    @FXML
    private Button btnJavaBrowser;
    @FXML
    private TextField txtResource;
    @FXML
    private Button btnResourceBrowser;
    @FXML
    private TextField txtEntityPackage;
    @FXML
    private TextField txtMapperPackage;
    @FXML
    private TextField txtMapperName;
    @FXML
    private ComboBox cboGenType;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnSave;
    @FXML
    private CheckBox chkOverwrite;
    @FXML
    private MenuItem mnuAddSource;
    @FXML
    private MenuItem mnuModifySource;
    @FXML
    private MenuItem mnuDeleteSource;

    private GenSettings genSettings;
    private DataSourceConfig dataSourceConfig;

    @Override
    public void onLoad() {
        listView.setCellFactory(lv -> new DataSourceConfigViewCell());
        GenType[] types = GenType.values();
        for (GenType type : types) {
            try {
                Description description = EnumsUtils.getService(type, Description.class);
                genTypes.add(new NameValue<>(description.value(), type));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        this.cboGenType.setItems(this.genTypes);
        this.listView.setItems(ApplicationContext.getInstance().getDataSourceConfigs());
        this.setGenSettings(new GenSettings());
        this.initEvents();
    }

    private void initEvents() {
        this.listView.getSelectionModel().selectedItemProperty().addListener(this::lstViewOnChanged);

        this.btnAddSource.setOnAction(this::btnAddSourceAction);
        this.btnRemoveSource.setOnAction(this::btnRemoveSource);

        this.mnuAddSource.setOnAction(this::btnAddSourceAction);
        this.mnuDeleteSource.setOnAction(this::btnRemoveSource);

        this.mnuModifySource.setOnAction(this::mnuModifyAction);

        this.btnJavaBrowser.setOnAction(this::btnJavaBrowserAction);
        this.btnResourceBrowser.setOnAction(this::btnResourceBrowserAction);

        this.btnSave.setOnAction(this::btnSaveAction);
    }

    public void btnAddSourceAction(ActionEvent event) {
        try {
            ApplicationContext.getInstance().show(StageType.DataSource, "新增数据源", Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void mnuModifyAction(ActionEvent event) {
        try {
            ApplicationContext.getInstance().show(StageType.DataSource, "修改数据源", Modality.APPLICATION_MODAL);
            Window dataSourceWindow = ApplicationContext.getInstance().getWindow(StageType.DataSource);
            DataSourceController dataSourceController = (DataSourceController) dataSourceWindow.getController();
            dataSourceController.setDataSourceConfig(this.dataSourceConfig);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void ctxShown(WindowEvent event) {
        if (listView.getSelectionModel().getSelectedItem() == null) {
            mnuAddSource.setDisable(false);
            mnuModifySource.setDisable(true);
            mnuDeleteSource.setDisable(true);
        } else {
            mnuAddSource.setDisable(false);
            mnuModifySource.setDisable(false);
            mnuDeleteSource.setDisable(false);
        }
    }

    public void btnRemoveSource(ActionEvent event) {
        DataSourceConfig config = (DataSourceConfig) this.listView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "确认要删除[" + config.getName() + "]数据源吗？", ButtonType.YES, ButtonType.NO);
        if (alert.showAndWait().get() == ButtonType.YES) {
            ApplicationContext.getInstance().removeDataSourceConfig(config);
        }
    }

    public void btnJavaBrowserAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(super.getWindow().getStage());
        if (file != null) {
            this.txtJava.setText(file.getAbsolutePath());
        }
    }

    public void btnResourceBrowserAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(super.getWindow().getStage());
        if (file != null) {
            this.txtResource.setText(file.getAbsolutePath());
        }
    }

    public void btnSaveAction(ActionEvent event) {
        if (!validate())
            return;
        this.genSettings.setEntityName(this.txtEntityName.getText().trim());
        this.genSettings.setMapperName(this.txtMapperName.getText().trim());
        this.genSettings.setJavaOutput(this.txtJava.getText().trim());
        this.genSettings.setResourceOutput(this.txtResource.getText().trim());
        this.genSettings.setEntityPackage(this.txtEntityPackage.getText().trim());
        this.genSettings.setMapperPackage(this.txtMapperPackage.getText().trim());
        this.genSettings.setGenType(((NameValue<GenType>) this.cboGenType.getSelectionModel().getSelectedItem()).getValue());
        this.genSettings.setOverwrite(this.chkOverwrite.isSelected());
        ApplicationContext.getInstance().saveGenSettings();
    }

    private boolean validate() {
        if (StringUtils.isEmpty(this.txtEntityName.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "实体类命名规则不能为空！格式\"${EntityName}\"", ButtonType.OK).showAndWait();
            return false;
        }
        if (!this.txtEntityName.getText().contains("${EntityName}")) {
            new Alert(Alert.AlertType.ERROR, "实体类命名规则必须包含\"${EntityName}\"！", ButtonType.OK).showAndWait();
            return false;
        }
        if (StringUtils.isEmpty(this.txtMapperName.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "Mapper接口命名规则不能为空！格式\"${EntityName}\"Mapper", ButtonType.OK).showAndWait();
            return false;
        }
        if (!this.txtMapperName.getText().contains("${EntityName}")) {
            new Alert(Alert.AlertType.ERROR, "Mapper接口命名规则必须包含\"${EntityName}\"！", ButtonType.OK).showAndWait();
            return false;
        }
        if (StringUtils.isEmpty(this.txtJava.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "Java代码输出路径不能为空！", ButtonType.OK).showAndWait();
            return false;
        }
        if (((NameValue<GenType>) this.cboGenType.getSelectionModel().getSelectedItem()).getValue() == GenType.XmlMapper &&
                StringUtils.isEmpty(this.txtJava.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "Mapper映射输出路径不能为空！", ButtonType.OK).showAndWait();
            return false;
        }
        if (StringUtils.isEmpty(this.txtEntityPackage.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "实体类包名不能为空！", ButtonType.OK).showAndWait();
            return false;
        }
        if (StringUtils.isEmpty(this.txtMapperPackage.getText().trim())) {
            new Alert(Alert.AlertType.ERROR, "Mapper接口包名不能为空！", ButtonType.OK).showAndWait();
            return false;
        }
        return true;
    }

    private void setGenSettings(GenSettings settings) {
        this.txtEntityName.setText(settings.getEntityName());
        this.txtMapperName.setText(settings.getMapperName());
        this.txtResource.setText(settings.getResourceOutput());
        this.txtEntityPackage.setText(settings.getEntityPackage());
        this.txtMapperPackage.setText(settings.getMapperPackage());
        this.cboGenType.getSelectionModel().select(CollectionUtils.findOne(this.genTypes, (item) -> item.getValue().equals(settings.getGenType())));
        this.chkOverwrite.setSelected(settings.isOverwrite());
    }

    private void lstViewOnChanged(ObservableValue observable, Object oldValue, Object newValue) {
        if (newValue != null) {
            this.dataSourceConfig = (DataSourceConfig) newValue;
            this.genSettings = ApplicationContext.getInstance().getGenSettings(this.dataSourceConfig.getName());
            if (this.genSettings != null) {
                setGenSettings(this.genSettings);
            } else {
                ApplicationContext.getInstance().addDataSourceConfig(this.dataSourceConfig);
                this.genSettings = ApplicationContext.getInstance().getGenSettings(this.dataSourceConfig.getName());
            }
            this.btnRemoveSource.setDisable(false);
            this.btnSave.setDisable(false);
            this.btnStart.setDisable(false);
        } else {
            this.dataSourceConfig = null;
            this.genSettings = null;

            this.btnRemoveSource.setDisable(true);
            this.btnSave.setDisable(true);
            this.btnStart.setDisable(true);
        }
    }

    protected void refreshListView() {
        this.listView.refresh();
    }
}
