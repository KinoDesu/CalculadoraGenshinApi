package dev.kinodesu.model.response.calculator.item;

public class SkillInfo {
    private int id;
    private String level_current;
    private String level_target;

    public SkillInfo() {
    }

    public SkillInfo(int id, String level_current, String level_target) {
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

    public String getLevel_current() {
        return level_current;
    }

    public void setLevel_current(String level_current) {
        this.level_current = level_current;
    }

    public String getLevel_target() {
        return level_target;
    }

    public void setLevel_target(String level_target) {
        this.level_target = level_target;
    }
}
