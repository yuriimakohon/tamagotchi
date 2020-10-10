package world.ucode.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class SceneManager {
    public enum States {
        MAIN_MENU("/world/ucode/views/MainMenu.fxml"),
        CREATE_MENU("/world/ucode/views/CreateMenu.fxml");

        private final String url;

        States(String url) {
            this.url = url;
        }
    }

    private static SceneManager instance;
    private static final HashMap<String, Scene> scenes = new HashMap<>();
    private static Stage stage;

    private SceneManager() {}

    public static void setStage(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException();
        }
        SceneManager.stage = stage;
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public static void switchScene(States state) {
        Scene scene = scenes.computeIfAbsent(state.url, u -> {
            try {
                return new Scene(FXMLLoader.load(SceneManager.class.getResource(u)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        stage.setScene(scene);
    }
}
