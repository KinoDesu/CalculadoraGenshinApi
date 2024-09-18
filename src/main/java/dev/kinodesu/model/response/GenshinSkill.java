package dev.kinodesu.model.response;

public class GenshinSkill {
    private int id;
    private int group_id;
    private String name;
    private String icon;
    private int max_level;
    private int level_current;

    public GenshinSkill() {
    }

    public GenshinSkill(int id, int group_id, String name, String icon, int max_level, int level_current) {
        this.id = id;
        this.group_id = group_id;
        this.name = name;
        this.icon = icon;
        this.max_level = max_level;
        this.level_current = level_current;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMax_level() {
        return max_level;
    }

    public void setMax_level(int max_level) {
        this.max_level = max_level;
    }

    public int getLevel_current() {
        return level_current;
    }

    public void setLevel_current(int level_current) {
        this.level_current = level_current;
    }
}
