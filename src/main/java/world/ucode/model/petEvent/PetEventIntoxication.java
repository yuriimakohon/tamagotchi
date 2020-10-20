package world.ucode.model.petEvent;

import world.ucode.model.pet.Pet;

class PetEventIntoxication extends PetEvent {
    private final int healthChanger;
    private final int hungerChanger;
    private final int happinessChanger;

    PetEventIntoxication() {
        super(true);
        healthChanger = -10;
        hungerChanger = -80;
        happinessChanger = -10;
        name = "Intoxication";
        description = "Something was wrong with last repast:" +
                "\nHunger " + hungerChanger +
                "\nHealth " + healthChanger +
                "\nHappiness " + happinessChanger;
    }

    @Override
    void runEvent(Pet pet) {
        pet.getHealth().addValue(healthChanger);
        pet.getHappiness().addValue(happinessChanger);
        pet.getHunger().addValue(hungerChanger);
    }
}
