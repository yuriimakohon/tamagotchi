package world.ucode.model.petEvent;

import world.ucode.model.pet.Pet;

public class PetEventChoked extends PetEvent {
    private final int healthChanger;
    private final int happinessChanger;
    private final int cleanlinessChanger;

    @Override
    void runEvent(Pet pet) {
    }

    PetEventChoked() {
        super(true);
        healthChanger = -5;
        happinessChanger = -5;
        cleanlinessChanger = -5;
        name = "Choked";
        description = "Something got in the wrong throat:" +
            "\nHealth " + healthChanger +
            "\nCleanliness " + cleanlinessChanger +
            "\nHappiness " + happinessChanger;
    }
}
