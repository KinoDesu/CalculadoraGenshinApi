package dev.kinodesu.model.response.calculator.material_consume.item;

public class CalculatorCharacter {
    private int id;
    private String icon;
    private int avatar_level;
    private int avatar_current_level;
    private int avatar_target_level;
    private String name;
    private int element_attr_id;

    public CalculatorCharacter() {
    }

    public CalculatorCharacter(int id, String icon, int avatar_level, int avatarCurrentLevel, int avatarTargetLevel, String name,
                               int element_attr_id) {
        this.id = id;
        this.icon = icon;
        this.avatar_level = avatar_level;
        avatar_current_level = avatarCurrentLevel;
        avatar_target_level = avatarTargetLevel;
        this.name = name;
        this.element_attr_id = element_attr_id;
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

    public int getAvatar_level() {
        return avatar_level;
    }

    public void setAvatar_level(int avatar_level) {
        this.avatar_level = avatar_level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElement_attr_id() {
        return element_attr_id;
    }

    public void setElement_attr_id(int element_attr_id) {
        this.element_attr_id = element_attr_id;
    }

    public int getAvatar_target_level() {
        return avatar_target_level;
    }

    public void setAvatar_target_level(int avatar_target_level) {
        this.avatar_target_level = avatar_target_level;
    }

    public int getAvatar_current_level() {
        return avatar_current_level;
    }

    public void setAvatar_current_level(int avatar_current_level) {
        this.avatar_current_level = avatar_current_level;
    }
}
