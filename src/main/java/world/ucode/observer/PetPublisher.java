package world.ucode.observer;

import world.ucode.model.Pet;
import world.ucode.model.stat.Stat;

public interface PetPublisher {
    void registerObserver(PetObserver o);
    void notifyAllStats(Pet pet);
    void notifyStat(Stat stat);
}
