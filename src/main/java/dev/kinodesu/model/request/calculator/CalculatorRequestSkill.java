package dev.kinodesu.model.request.calculator;

public class CalculatorRequestSkill {
    private int id;
    private int level_current;
    private int level_target;

    public CalculatorRequestSkill() {
    }

    public CalculatorRequestSkill(int id, int level_current, int level_target) {
        this.id = id;
        this.level_current = level_current;
        this.level_target = level_target;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel_current() {
        return level_current;
    }

    public void setLevel_current(int level_current) {
        this.level_current = level_current;
    }

    public int getLevel_target() {
        return level_target;
    }

    public void setLevel_target(int level_target) {
        this.level_target = level_target;
    }
}
