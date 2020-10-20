package world.ucode.control;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import world.ucode.model.pet.Pet;
import world.ucode.view.PetView;

public class PetController extends PetView {
    @FXML private ToggleGroup togglegroupFur;

    static void createNewPet(String name, Pet.Species type, int maxHealth) {
        pet.init(name, type, maxHealth);
        SaveManager.savePet(pet);
        pet.start();
    }

    public void initialize() {
        togglegroupFur.selectedToggleProperty().addListener(e -> {
            RadioButton rbtn = (RadioButton) togglegroupFur.getSelectedToggle();

            if (rbtn.getId().equals("rbtnGinger"))
                setImageViewPet(1);
            else if (rbtn.getId().equals("rbtnLight"))
                setImageViewPet(2);
            else
                setImageViewPet(3);
        });
    }

    static void loadPetSave(int saveId) {
        SaveManager.loadPet(saveId, pet);
        pet.notifyAllStats(pet);
        pet.start();
    }

    @FXML
    void petCure() {
        pet.cure();
    }

    @FXML
    void petFeed() {
        pet.feed();
    }

    @FXML
    void petGiveDrink() {
        pet.giveDrink();
    }

    @FXML
    void petPlay() {
        pet.play();
    }

    @FXML
    void petClean() {
        pet.clean();
    }
}
