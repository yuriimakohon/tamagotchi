package world.ucode.view;

import world.ucode.model.pet.Pet;
import world.ucode.model.stat.Stat;

public interface PetObserver {
    void initObserver(Pet pet);
    void updateAllStats(Pet pet);
    void updateStat(Stat stat);
}
