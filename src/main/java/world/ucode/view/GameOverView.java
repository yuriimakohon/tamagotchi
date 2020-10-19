package world.ucode.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverView {
    @FXML Label lblReason;
    @FXML Label lblInfo;

    protected GameOverView() {}

    protected void setLblInfo(String petName) {
        String info = lblInfo.getText();
        info = info.substring(0, 19) + petName + info.substring(18);
        lblInfo.setText(info);
    }
    protected void setLblReason(String text) {
        lblReason.setText(text);
    }
}
