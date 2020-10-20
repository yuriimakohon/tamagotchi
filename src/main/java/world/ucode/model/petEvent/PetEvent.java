package world.ucode.model.petEvent;

import world.ucode.model.pet.Pet;

public abstract class PetEvent {
    public enum Type {
        INTOXICATION,
        SATIETY,
        INJURY,
        CHOKED,
    }

    protected String name;
    protected String description;
    protected boolean negative;

    protected PetEvent(boolean isNegative) {
        negative = isNegative;
    }

    abstract void runEvent(Pet pet);

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isNegative() {
        return negative;
    }
}
