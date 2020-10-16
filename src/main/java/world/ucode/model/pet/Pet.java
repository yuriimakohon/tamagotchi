package world.ucode.model.pet;

import javafx.application.Platform;
import world.ucode.control.SaveManager;
import world.ucode.model.stat.*;
import world.ucode.view.PetObserver;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        health.update();
        hunger.update();
        thirst.update();
        happiness.update();
        cleanliness.update();
        notifyAllStats(this);
    }

    public void init(String name, Species specie) {
        this.name = name;
        if (specie == Species.CAT){
            health = new HealthStat(250, 0);
            hunger = new HungerStat( 300, -0.5f);
            thirst = new ThirstStat(200, -0.8f);
            cleanliness = new CleanlinessStat(100, -0.1f);
        } else if (specie == Species.DOG) {
            health = new HealthStat(400, 0);
            hunger = new HungerStat(350, -1);
            thirst = new ThirstStat(200, -1);
            cleanliness = new CleanlinessStat(100, -0.3f);
        }
        happiness = new HappinessStat(100, 0);
        observer.initObserver(this);
    }

    // Actions with Pet
    public void cure() {
        health.setValue(health.getValue() + 40);
        notifyStat(health);
    }
    public void feed() {
        hunger.setValue(hunger.getValue() + 60);
        notifyStat(hunger);
    }
    public void giveDrink() {
        thirst.setValue(thirst.getValue() + 40);
        notifyStat(thirst);
    }
    public void clean() {
        cleanliness.setValue(cleanliness.getMaxValue());
        notifyStat(cleanliness);
    }
    public void play() {
        happiness.setValue(happiness.getValue() + 30);
        notifyStat(happiness);
    }

    // Stat getters
    private Pet getInstance() {
        return this;
    }

    public String getName() {
        return name;
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
