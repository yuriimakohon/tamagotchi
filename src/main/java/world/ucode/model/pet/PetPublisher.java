package world.ucode.model.pet;

import world.ucode.model.stat.Stat;
import world.ucode.view.PetObserver;

public interface PetPublisher {
    void registerObserver(PetObserver o);
    void notifyAllStats(Pet pet);
    void notifyStat(Stat stat);
}
