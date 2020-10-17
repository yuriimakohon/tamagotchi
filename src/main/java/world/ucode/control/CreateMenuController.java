package world.ucode.control;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import world.ucode.model.pet.Pet;
import world.ucode.view.CreateMenuView;

public class CreateMenuController extends CreateMenuView {
    @FXML private TextField tfieldPetName;
    @FXML private Slider sliderMaxHealth;
    @FXML private ToggleGroup togglegroupPetType;
    @FXML private RadioButton rbuttonDog;
    @FXML private RadioButton rbuttonCat;

    public void initialize() {
        rbuttonCat.setUserData(Pet.Species.CAT);
        rbuttonDog.setUserData(Pet.Species.DOG);

        tfieldPetName.lengthProperty().addListener((observableValue, number, t1) -> {
            setDisableBtnCreate(tfieldPetName.getText().isEmpty());
            if (tfieldPetName.getText().length() > 20)
                tfieldPetName.setText(tfieldPetName.getText().substring(0, 20));
        });

        sliderMaxHealth.valueProperty().addListener((changed, oldValue, newValue) ->
            setLblMaxHealthValue(200 + newValue.intValue() * 50));
    }

    @FXML
    void createNewPet() {
        Pet.Species type = (Pet.Species) togglegroupPetType.getSelectedToggle().getUserData();
        PetController.createNewPet(tfieldPetName.getText(), type, getLblMaxHealthValue());
        SceneManager.switchScene(SceneManager.States.PET);
    }
}
