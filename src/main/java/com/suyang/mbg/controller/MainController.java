package com.suyang.mbg.controller;

import com.suyang.mbg.context.ApplicationContext;
import com.suyang.mbg.context.StageType;
import com.suyang.mbg.enums.GenType;
import com.suyang.mbg.utils.Description;
import com.suyang.mbg.utils.EnumsUtils;
import com.suyang.mbg.utils.NameValue;
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
    public void btnAddSourceAction(ActionEvent event) throws IOException, NoSuchFieldException {
        ApplicationContext.getInstance().show(StageType.DataSource, "", Modality.APPLICATION_MODAL);
    }

    @FXML
    public void btnRemoveSource(ActionEvent event) {

    }

    @FXML
    public void btnJavaBrowserAction(ActionEvent event) {

    }

    public void btnResourceBrowser(ActionEvent event) {

    }

    @Override
    public void onLoad() {
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
        this.clear();
    }

    private void clear() {
        this.txtEntityName.setText("${EntityName}");
        this.txtMapperName.setText("${EntityName}Mapper");
        this.txtJava.setText("");
        this.txtResource.setText("");
        this.txtEntityPackage.setText("com.example.entities");
        this.txtMapperPackage.setText("com.example.mapper");
        this.cboGenType.getSelectionModel().select(0);
        this.btnStart.setDisable(true);
    }
}
