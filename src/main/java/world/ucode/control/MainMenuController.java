package world.ucode.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import world.ucode.model.MainMenuModel;
import world.ucode.view.MainMenuView;

public class MainMenuController extends MainMenuView {
    private final MainMenuModel model = new MainMenuModel();

    @FXML
    private void exitGame() {
        Platform.exit();
    }

    @FXML
    private void say() {
        setLabelText("Hello!");
    }

    @FXML
    private void newGame() {
        SceneManager.switchScene(SceneManager.States.CREATE_MENU);
    }
}
