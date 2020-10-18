package world.ucode.model.petEvent;

import world.ucode.model.pet.Pet;

class PetEventSatiety extends PetEvent {
    private final int hungerChanger;
    private final int happinessChanger;

    PetEventSatiety() {
        hungerChanger = 10;
        happinessChanger = 7;
        name = "Satiety";
        description = "The pet ate well and feels full:\n" +
                "Hunger +" + hungerChanger +
                "\nHappiness +" + happinessChanger;
    }

    @Override
    void runEvent(Pet pet) {
        pet.getHappiness().addValue(happinessChanger);
        pet.getHunger().addValue(hungerChanger);
    }
}
