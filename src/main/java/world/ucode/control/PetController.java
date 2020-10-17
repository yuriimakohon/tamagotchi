package world.ucode.control;

import javafx.fxml.FXML;
import world.ucode.model.pet.Pet;
import world.ucode.view.PetView;

public class PetController extends PetView {
    static void createNewPet(String name, Pet.Species type, int maxHealth) {
        pet.init(name, type, maxHealth);
        SaveManager.savePet(pet);
        pet.start();
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
