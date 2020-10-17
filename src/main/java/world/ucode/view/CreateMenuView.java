package world.ucode.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CreateMenuView {
    @FXML private Label lblMaxHealthValue;
    @FXML private Button btnCreate;

    protected CreateMenuView() {}

    protected void setLblMaxHealthValue(int value) {
        lblMaxHealthValue.setText(String.valueOf(value));
    }
    protected int getLblMaxHealthValue() {
        return Integer.parseInt(lblMaxHealthValue.getText());
    }
    protected void setDisableBtnCreate(boolean isDisable) {
        btnCreate.setDisable(isDisable);
    }
}
