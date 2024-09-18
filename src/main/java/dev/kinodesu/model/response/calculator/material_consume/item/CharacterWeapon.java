package dev.kinodesu.model.response.calculator.material_consume.item;

public class CharacterWeapon {
    private int id;
    private String icon;
    private int weapon_level;
    private int weapon_target_level;
    private String name;

    public CharacterWeapon(int id, String icon, int weapon_level, int weaponTargetLevel, String name) {
        this.id = id;
        this.icon = icon;
        this.weapon_level = weapon_level;
        weapon_target_level = weaponTargetLevel;
        this.name = name;
    }

    public CharacterWeapon() {
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

    public int getWeapon_target_level() {
        return weapon_target_level;
    }

    public void setWeapon_target_level(int weapon_target_level) {
        this.weapon_target_level = weapon_target_level;
    }
}
