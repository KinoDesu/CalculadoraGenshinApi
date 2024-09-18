package dev.kinodesu.model.request.calculator;

import java.util.List;

public class CalculatorRequestCharacter {
    private int avatar_id;
    private int avatar_level_current;
    private int avatar_level_target;
    private int element_attr_id;
    private List<CalculatorRequestSkill> skill_list;
    private CalculatorRequestWeapon weapon;

    public CalculatorRequestCharacter() {
    }

    public CalculatorRequestCharacter(int avatar_id, int avatar_level_current, int avatar_level_target, int element_attr_id, List<CalculatorRequestSkill> skill_list, CalculatorRequestWeapon weapon) {
        this.avatar_id = avatar_id;
        this.avatar_level_current = avatar_level_current;
        this.avatar_level_target = avatar_level_target;
        this.element_attr_id = element_attr_id;
        this.skill_list = skill_list;
        this.weapon = weapon;
    }

    public int getAvatar_id() {
        return avatar_id;
    }

    public void setAvatar_id(int avatar_id) {
        this.avatar_id = avatar_id;
    }

    public int getAvatar_level_current() {
        return avatar_level_current;
    }

    public void setAvatar_level_current(int avatar_level_current) {
        this.avatar_level_current = avatar_level_current;
    }

    public int getAvatar_level_target() {
        return avatar_level_target;
    }

    public void setAvatar_level_target(int avatar_level_target) {
        this.avatar_level_target = avatar_level_target;
    }

    public int getElement_attr_id() {
        return element_attr_id;
    }

    public void setElement_attr_id(int element_attr_id) {
        this.element_attr_id = element_attr_id;
    }

    public List<CalculatorRequestSkill> getSkill_list() {
        return skill_list;
    }

    public void setSkill_list(List<CalculatorRequestSkill> skill_list) {
        this.skill_list = skill_list;
    }

    public CalculatorRequestWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(CalculatorRequestWeapon weapon) {
        this.weapon = weapon;
    }
}
