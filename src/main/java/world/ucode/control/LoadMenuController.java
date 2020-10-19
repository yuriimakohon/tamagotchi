package world.ucode.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import world.ucode.view.LoadMenuView;

public class LoadMenuController extends LoadMenuView {
    public void initialize() {
        ObservableList<HBox> ol = FXCollections.observableArrayList();
        SaveManager.loadSaves(ol);
        listViewSaves.setItems(ol);
    }

    @FXML
    public void selectSave() {
        HBox save = (HBox) listViewSaves.getSelectionModel().getSelectedItem();
        if (save != null) {
            SaveManager.updateCurrentSaveId((int) save.getUserData());
            setDisableButtons(false);
        }
    }

    @FXML
    public void loadGame() {
        PetController.loadPetSave(SaveManager.getCurrentSaveId());
        SceneManager.switchScene(SceneManager.States.PET);
    }

    @FXML
    public void newGame() {
        SceneManager.switchScene(SceneManager.States.CREATE_MENU);
    }

    @FXML
    public void deleteGame() {
        setDisableButtons(true);
        HBox save = (HBox) listViewSaves.getSelectionModel().getSelectedItem();
        SaveManager.deletePet((int) save.getUserData());
        listViewSaves.getItems().remove(save);
    }
}
