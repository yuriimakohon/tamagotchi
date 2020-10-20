package world.ucode.model.pet;

import world.ucode.model.petEvent.PetEvent;
import world.ucode.model.stat.Stat;
import world.ucode.view.PetObserver;

public interface PetPublisher {
    void registerObserver(PetObserver o);
    void notifyAllStats(Pet pet);
    void notifyStat(Stat stat);
    void notifyGameOver(Stat.Type reason);
    void notifySkin(int skin);
    void notifyPetEvent(PetEvent event);
}
