package com.suyang.mbg.controller;

import com.suyang.mbg.context.ApplicationContext;
import com.suyang.mbg.context.StageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Modality;

import java.io.IOException;

public class MainController extends BaseController {
    @FXML
    public void handleAddSourceButtonAction(ActionEvent event) throws IOException, NoSuchFieldException {
        ApplicationContext.getInstance().show(StageType.DataSource, "", Modality.APPLICATION_MODAL);
    }

    @FXML
    public void handleRemoveSourceButtonAction(ActionEvent event) {

    }
}
