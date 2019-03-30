package com.suyang.mbg.controller;

import com.suyang.mbg.database.domain.DataSourceConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class DataSourceConfigViewCell extends ListCell<DataSourceConfig> {

    @FXML
    private Label lblName;
    @FXML
    private GridPane panel;
    @FXML
    private ImageView image;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(DataSourceConfig config, boolean empty) {
        super.updateItem(config, empty);

        if (empty || config == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/datasource_cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            panel.prefWidthProperty().bind(getListView().widthProperty().subtract(20));
            lblName.setText(config.getName());
            setText(null);
            setGraphic(panel);
        }
    }
}
