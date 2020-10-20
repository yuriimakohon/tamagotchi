package world.ucode.model.petEvent;

import world.ucode.model.pet.Pet;

public abstract class PetEvent {
    public enum Type {
        INTOXICATION,
        SATIETY,
        INJURY,
    }

    protected String name = "Pet event name";
    protected String description = "Pet event description";

    abstract void runEvent(Pet pet);

    public static void tryEvent(Type type, int chance, Pet pet) {
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
            event.printEventInfo();
        }
    }

    private static boolean randPercent(int chance) {
        return chance >= (int) (Math.random() * 101) + 1;
    }

    protected void printEventInfo() {
        System.out.println(name + "\n=================\n" + description + "\n");
    }
}
