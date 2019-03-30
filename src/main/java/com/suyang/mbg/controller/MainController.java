package com.suyang.mbg.controller;

import com.suyang.mbg.context.ApplicationContext;
import com.suyang.mbg.context.GenSettings;
import com.suyang.mbg.context.StageType;
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
import javafx.stage.Modality;

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
    public void btnAddSourceAction(ActionEvent event) throws IOException, NoSuchFieldException {
        ApplicationContext.getInstance().show(StageType.DataSource, "", Modality.APPLICATION_MODAL);
    }

    @FXML
    public void btnRemoveSource(ActionEvent event) {

    }

    @FXML
    public void btnJavaBrowserAction(ActionEvent event) {

    }

    @FXML
    public void btnResourceBrowser(ActionEvent event) {

    }

    @FXML
    public void btnSaveAction(ActionEvent event) {
        ApplicationContext.getInstance().saveGenSettings();
    }

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
        this.listView.getSelectionModel().selectedItemProperty().addListener(this::lstViewOnChanged);
        setGenSettings(new GenSettings());
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
        DataSourceConfig config = (DataSourceConfig) newValue;
        GenSettings settings = ApplicationContext.getInstance().getGenSettings(config.getName());
        if (settings != null) {
            setGenSettings(settings);
        } else {
            ApplicationContext.getInstance().addDataSourceConfig(config);
        }
    }
}
