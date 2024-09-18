package dev.kinodesu.model.response.calculator;

public class CalculatorWeapon {
    private int id;
    private String icon;
    private int weapon_level;
    private String name;

    public CalculatorWeapon(int id, String icon, int weapon_level, String name) {
        this.id = id;
        this.icon = icon;
        this.weapon_level = weapon_level;
        this.name = name;
    }

    public CalculatorWeapon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getWeapon_level() {
        return weapon_level;
    }

    public void setWeapon_level(int weapon_level) {
        this.weapon_level = weapon_level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
