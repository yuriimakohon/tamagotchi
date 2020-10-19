package world.ucode.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import world.ucode.view.GameOverView;

public class GameOverController extends GameOverView {
    @FXML
    private void exitGame() {
        Platform.exit();
    }

    public void setGameOverInfo(String reason, String petName) {
        setLblInfo(petName);
        setLblReason(reason);
    }
}
