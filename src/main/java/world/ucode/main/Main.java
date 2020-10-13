package world.ucode.main;

import javafx.application.Application;
import javafx.stage.Stage;
import world.ucode.control.SceneManager;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneManager.setStage(primaryStage);
        SceneManager.switchScene(SceneManager.States.MAIN_MENU);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}