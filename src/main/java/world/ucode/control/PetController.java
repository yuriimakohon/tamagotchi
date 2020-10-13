package world.ucode.control;

import javafx.fxml.FXML;
import world.ucode.model.pet.Pet;
import world.ucode.view.PetView;

public class PetController extends PetView {
    static void initPet(String name) {
        pet.init(name, Pet.Species.CAT);
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
