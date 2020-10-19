package world.ucode.control;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenuController {
    @FXML
    private void exitGame() {
        Platform.exit();
    }

    @FXML
    private void newGame() {
        SceneManager.switchScene(SceneManager.States.CREATE_MENU);
    }

    @FXML
    private void loadGame() {
        SceneManager.switchScene(SceneManager.States.LOAD_MENU);
    }
}
