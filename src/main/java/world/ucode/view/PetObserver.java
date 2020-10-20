package world.ucode.view;

import world.ucode.model.pet.Pet;
import world.ucode.model.petEvent.PetEvent;
import world.ucode.model.stat.Stat;

public interface PetObserver {
    void updatePetEvent(PetEvent event);
    void gameOver(Stat.Type reason);
    void updateAllStats(Pet pet);
    void updateStat(Stat stat);
    void initObserver(Pet pet);
    void updateSkin(int skin);
}
