package world.ucode.control;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateMenuController {
    @FXML
    private TextField tfieldPetName;

    @FXML
    void createNewPet() {
        PetController.createNewPet(tfieldPetName.getText());
        SceneManager.switchScene(SceneManager.States.PET);
    }
}
