package world.ucode.model.petEvent;

import world.ucode.model.pet.Pet;

public class PetEventManager {
    public void tryEvent(PetEvent.Type type, int chance, Pet pet) {
        if (randPercent(chance)) {
            PetEvent event;
            switch (type) {
                case INTOXICATION:
                    event = new PetEventIntoxication();
                    break;
                case SATIETY:
                    event = new PetEventSatiety();
                    break;
                case INJURY:
                    event = new PetEventInjury();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
            event.runEvent(pet);
            pet.notifyAllStats(pet);
            pet.notifyPetEvent(event);
        }
    }

    private static boolean randPercent(int chance) {
        return chance >= (int) (Math.random() * 101) + 1;
    }
}
