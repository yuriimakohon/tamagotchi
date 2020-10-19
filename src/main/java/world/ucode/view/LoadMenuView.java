package world.ucode.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class LoadMenuView {
    @FXML protected ListView listViewSaves;
    @FXML private Button btnLoadGame;
    @FXML private Button btnDeleteGame;

    public static HBox createSavePane(String type, String name, String health, String happiness) {
        Font font = new Font("Comic Sans MS", 16);
        Label lblType = new Label(type);
        Label lblName = new Label(name);
        Label lblHealth = new Label(health);
        Label lblHappiness = new Label(happiness);
        lblName.setFont(font);
        lblHealth.setFont(font);
        lblHappiness.setFont(font);
        lblHealth.setStyle("-fx-text-fill: rgb(200, 30, 0);");
        lblHappiness.setStyle("-fx-text-fill: rgb(200, 200, 0);");

        HBox hbox = new HBox();
        hbox.setPrefHeight(50);
        hbox.setSpacing(20);
        hbox.getChildren().addAll(lblType, lblName, lblHealth, lblHappiness);
        return hbox;
    }

    protected void setDisableButtons(boolean isDisable) {
        btnDeleteGame.setDisable(isDisable);
        btnLoadGame.setDisable(isDisable);
    }
}
