package world.ucode.model.petEvent;

import world.ucode.model.pet.Pet;

class PetEventInjury extends PetEvent {
    private final int healthChanger;
    private final int happinessChanger;

    PetEventInjury() {
        super(true);
        healthChanger = -50;
        happinessChanger = -35;
        name = "Injury";
        description = "Jumped unsuccessfully while playing:" +
                "\nHealth " + healthChanger +
                "\nHappiness " + happinessChanger;
    }

    @Override
    void runEvent(Pet pet) {
        pet.getHealth().addValue(healthChanger);
        pet.getHappiness().addValue(happinessChanger);
    }
}