package world.ucode.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import world.ucode.control.GameOverController;
import world.ucode.control.SaveManager;
import world.ucode.control.SceneManager;
import world.ucode.model.pet.Pet;
import world.ucode.model.stat.Stat;

public class PetView implements PetObserver {
    protected static final Pet pet = new Pet();
    @FXML private Label lblName;
    @FXML private Label lblHealthStat;
    @FXML private Label lblHungerStat;
    @FXML private Label lblThirstStat;
    @FXML private Label lblHappinessStat;
    @FXML private Label lblCleanlinessStat;
    @FXML private ImageView imageViewPet;

    protected PetView() {
        pet.registerObserver(this);
    }

    protected void setLblName(String name) { lblName.setText(name); }
    protected void setLblHealthStat(int value) {
        lblHealthStat.setText(value + "");
    }
    protected void setLblHungerStat(int value) {
        lblHungerStat.setText(value + "");
    }
    protected void setLblThirstStat(int value) {
        lblThirstStat.setText(value + "");
    }
    protected void setLblHappinessStat(int value) {
        lblHappinessStat.setText(value + "");
    }
    protected void setLblCleanlinessStat(int value) {
        lblCleanlinessStat.setText(value + "");
    }
    protected void setImageViewPet(int skin) {
        String skinPath = "/world/ucode/img/";
        if (pet.getType() == Pet.Species.CAT)
            skinPath += "cat";
        else
            skinPath += "dog";
        skinPath += skin + ".png";
        imageViewPet.setImage(new Image(skinPath));
    }

    @Override
    public void updateSkin(int skin) {
        setImageViewPet(skin);
    }

    @Override
    public void updateStat(Stat stat) {
        Stat.Type type = stat.getType();
        int value = (int) stat.getValue();

        if (type == Stat.Type.HEALTH)
            setLblHealthStat(value);
        else if (type == Stat.Type.HUNGER)
            setLblHungerStat(value);
        else if (type == Stat.Type.THIRST)
            setLblThirstStat(value);
        else if (type == Stat.Type.HAPPINESS)
            setLblHappinessStat(value);
        else if (type == Stat.Type.CLEANLINESS)
            setLblCleanlinessStat(value);
    }

    @Override
    public void gameOver(Stat.Type reason) {
        String strReason;
        if (reason == Stat.Type.HEALTH) {
            strReason = "The pet expired... died, if in short";
        } else {
            strReason = "The pet did not feel happy and ran away";
        }
        GameOverController controller = SceneManager.getSceneLoader(SceneManager.States.GAME_OVER).getController();
        controller.setGameOverInfo(strReason, pet.getName());
        SceneManager.switchScene(SceneManager.States.GAME_OVER);
        SaveManager.deleteCurrentPet();
    }

    @Override
    public void updateAllStats(Pet pet) {
        updateStat(pet.getHealth());
        updateStat(pet.getHunger());
        updateStat(pet.getThirst());
        updateStat(pet.getHappiness());
        updateStat(pet.getCleanliness());
    }

    @Override
    public void initObserver(Pet pet) {
        setLblName(pet.getName());
        updateAllStats(pet);
    }
}
