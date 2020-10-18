package world.ucode.model.pet;

import javafx.application.Platform;
import world.ucode.control.SaveManager;
import world.ucode.model.petEvent.PetEvent;
import world.ucode.model.stat.*;
import world.ucode.view.PetObserver;

public class Pet implements PetPublisher {
    public enum Species {
        CAT,
        DOG,
    }

    // ==========| Publisher interface part |==========
    private PetObserver observer;

    @Override
    public void registerObserver(PetObserver o) {
        this.observer = o;
    }

    @Override
    public void notifyAllStats(Pet pet) {
        observer.updateAllStats(pet);
    }

    @Override
    public void notifyStat(Stat stat) {
        observer.updateStat(stat);
    }

    // ==============| Pet part |==============
    private String name;
    Species type;
    private Stat health;
    private Stat hunger;
    private Stat thirst;
    private Stat happiness;
    private Stat cleanliness;

    public void start() {
        Thread thread = new Thread(() -> {
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                    updateStats();
                    SaveManager.updatePetSave(getInstance());
                }
            };
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(updater);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void updateStats() {
        checkProsperity();
        health.update();
        hunger.update();
        thirst.update();
        happiness.update();
        cleanliness.update();
        notifyAllStats(this);
    }

    private void checkProsperity() {
        if (health.getValue() < health.getMaxValue() / 3) {
            happiness.addValue(-2);
            if (health.getMaxValue() == 0)
                System.out.println("GAME OVER: HEALTH");
        }
        if (happiness.getValue() < happiness.getMaxValue() / 2) {
            hunger.addValue(-2);
            if (happiness.getValue() < 20) {
                hunger.addValue(-2);
                if (happiness.getValue() == 0)
                    System.out.println("GAME OVER: HAPPINESS");
            }
        }
        if (hunger.getValue() < 40) {
            happiness.addValue(-1);
            if (hunger.getValue() == 0) {
                health.addValue(-3);
                happiness.addValue(-1);
            }
        }
        if (thirst.getValue() < 10) {
            happiness.addValue(-0.5f);
            if (thirst.getValue() == 0) {
                health.addValue(-2);
                happiness.addValue(-0.5f);
            }
        }
        if (cleanliness.getValue() < 20) {
            happiness.addValue(-2);
            if (cleanliness.getValue() == 0) {
                health.addValue(-1);
                happiness.addValue(-3);
            }
        }
    }

    public void init(String name, Species specie, int maxHealth) {
        this.name = name;
        if (specie == Species.CAT) {
            type = Species.CAT;
            hunger = new HungerStat( 300, -0.5f);
            thirst = new ThirstStat(200, -0.8f);
            cleanliness = new CleanlinessStat(100, -0.1f);
        } else if (specie == Species.DOG) {
            type = Species.DOG;
            hunger = new HungerStat(350, -1);
            thirst = new ThirstStat(200, -1);
            cleanliness = new CleanlinessStat(100, -0.3f);
        }
        health = new HealthStat(maxHealth, 0);
        happiness = new HappinessStat(100, 0);
        observer.initObserver(this);
    }

    // Actions with Pet
    public void cure() {
        health.setValue(health.getValue() + 20);
        notifyStat(health);
    }
    public void feed() {
        PetEvent.tryEvent(PetEvent.Type.INTOXICATION, 10, this);
        PetEvent.tryEvent(PetEvent.Type.SATIETY, 30, this);

        hunger.addValue(30);
        notifyStat(hunger);
    }
    public void giveDrink() {
        PetEvent.tryEvent(PetEvent.Type.INTOXICATION, 7, this);
        thirst.addValue(20);
        notifyStat(thirst);
    }
    public void clean() {
        cleanliness.setValue(cleanliness.getMaxValue());
        notifyStat(cleanliness);
    }
    public void play() {
        PetEvent.tryEvent(PetEvent.Type.INJURY, 10, this);
        happiness.setValue(happiness.getValue() + 10);
        notifyStat(happiness);
    }

    // Pet getters
    private Pet getInstance() {
        return this;
    }

    public String getName() {
        return name;
    }
    public Species getType() {
        return type;
    }

    public Stat getHealth() {
        return health;
    }
    public Stat getHunger() {
        return hunger;
    }
    public Stat getThirst() {
        return thirst;
    }
    public Stat getCleanliness() {
        return cleanliness;
    }
    public Stat getHappiness() {
        return happiness;
    }
}
