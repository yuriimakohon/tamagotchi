package world.ucode.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class SceneManager {
    public enum States {
        MAIN_MENU("/world/ucode/fxml/MainMenu.fxml"),
        CREATE_MENU("/world/ucode/fxml/CreateMenu.fxml"),
        PET("/world/ucode/fxml/Pet.fxml"),
        GAME_OVER("/world/ucode/fxml/GameOver.fxml");

        private final String url;

        States(String url) {
            this.url = url;
        }
    }

    private SceneManager() {}

    private static final HashMap<States, Scene> scenes = new HashMap<>();
    private static Stage stage;

    public static void setStage(Stage stage) {
        if (stage == null || SceneManager.stage != null) {
            throw new IllegalArgumentException();
        }
        SceneManager.stage = stage;

        for (States state : States.values()) {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(state.url));
            try {
                Scene scene = new Scene(loader.load());
                scene.setUserData(loader);
                scenes.put(state, scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static FXMLLoader getSceneLoader(SceneManager.States state) {
        return (FXMLLoader) scenes.get(state).getUserData();
    }

    public static void switchScene(States state) {
        stage.setScene(scenes.get(state));
    }
}
