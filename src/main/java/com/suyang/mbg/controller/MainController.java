package com.suyang.mbg.controller;

import com.suyang.mbg.context.ApplicationContext;
import com.suyang.mbg.context.Location;
import com.suyang.mbg.context.StageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Modality;

import java.io.IOException;

public class MainController {
    @FXML
    public void handlerBtnClick(ActionEvent event) throws IOException, NoSuchFieldException {
        ApplicationContext.getInstance().show(StageType.Main, "2222", Location.Empty(), Modality.APPLICATION_MODAL);
    }
}
