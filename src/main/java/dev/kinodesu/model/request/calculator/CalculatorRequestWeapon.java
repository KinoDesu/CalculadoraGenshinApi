package dev.kinodesu.model.request.calculator;

public class CalculatorRequestWeapon {
    private int id;
    private String name;
    private String icon;
    private int weapon_cat_id;
    private int weapon_level;
    private int max_level;
    private int level_current;
    private int level_target;

    public CalculatorRequestWeapon() {
    }

    public CalculatorRequestWeapon(int id, String name, String icon, int weapon_cat_id, int weapon_level, int max_level, int level_current, int level_target) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.weapon_cat_id = weapon_cat_id;
        this.weapon_level = weapon_level;
        this.max_level = max_level;
        this.level_current = level_current;
        this.level_target = level_target;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getWeapon_cat_id() {
        return weapon_cat_id;
    }

    public void setWeapon_cat_id(int weapon_cat_id) {
        this.weapon_cat_id = weapon_cat_id;
    }

    public int getWeapon_level() {
        return weapon_level;
    }

    public void setWeapon_level(int weapon_level) {
        this.weapon_level = weapon_level;
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

    public int getLevel_target() {
        return level_target;
    }

    public void setLevel_target(int level_target) {
        this.level_target = level_target;
    }
}
