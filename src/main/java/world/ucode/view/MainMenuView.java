package world.ucode.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainMenuView {
    @FXML
    private Label lbl;

    protected void setLabelText(String text) {
        lbl.setText(text);
    }
}
