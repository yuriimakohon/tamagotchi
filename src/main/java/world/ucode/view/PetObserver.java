package world.ucode.view;

import world.ucode.model.pet.Pet;
import world.ucode.model.stat.Stat;

public interface PetObserver {
    void initObserver(Pet pet);
    void updateAllStats(Pet pet);
    void updateStat(Stat stat);
    void gameOver(Stat.Type reason);
    void updateSkin(int skin);
}
