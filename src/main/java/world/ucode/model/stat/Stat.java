package world.ucode.model.stat;

public abstract class Stat {
    public enum Type {
        HEALTH,
        HUNGER,
        THIRST,
        HAPPINESS,
        CLEANLINESS
    }

    private final Type type;
    private final float maxValue;
    private float value;
    private float changer;

    protected Stat(Type type, float maxValue, float changer) {
        this.type = type;
        this.maxValue = maxValue;
        this.value = maxValue;
        this.changer = changer;
    }

    public Type getType() {
        return type;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setValue(float value) {
        this.value = Math.min(value, maxValue);
    }

    public float getValue() {
        return value;
    }

    public void update() {
        value = value + changer < 0 ? 0 : Math.min(value + changer, maxValue);
    }
}

