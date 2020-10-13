package world.ucode.observer;

import world.ucode.model.Pet;
import world.ucode.model.stat.Stat;

public interface PetObserver {
    void initObserver(Pet pet);
    void updateAllStats(Pet pet);
    void updateStat(Stat stat);
}
