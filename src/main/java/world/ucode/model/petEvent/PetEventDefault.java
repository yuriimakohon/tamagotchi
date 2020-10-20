package world.ucode.model.petEvent;

import world.ucode.model.pet.Pet;

public class PetEventDefault extends PetEvent {
    PetEventDefault() {
        super(false);
        name = "Default pet event name";
        description = "Default pet event description";
    }
    @Override
    void runEvent(Pet pet) {
    }
}
