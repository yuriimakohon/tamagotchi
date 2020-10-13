package world.ucode.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
