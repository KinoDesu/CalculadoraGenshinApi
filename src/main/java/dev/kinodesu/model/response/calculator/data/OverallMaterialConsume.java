package dev.kinodesu.model.response.calculator.data;

import dev.kinodesu.model.response.calculator.material_consume.MaterialConsume;

import java.util.List;

public class OverallMaterialConsume {
    private List<MaterialConsume> avatar_consume;
    private List<MaterialConsume> avatar_skill_consume;
    private List<MaterialConsume> weapon_consume;

    public OverallMaterialConsume() {
    }

    public OverallMaterialConsume(List<MaterialConsume> avatar_consume, List<MaterialConsume> avatar_skill_consume, List<MaterialConsume> weapon_consume) {
        this.avatar_consume = avatar_consume;
        this.avatar_skill_consume = avatar_skill_consume;
        this.weapon_consume = weapon_consume;
    }

    public List<MaterialConsume> getAvatar_consume() {
        return avatar_consume;
    }

    public void setAvatar_consume(List<MaterialConsume> avatar_consume) {
        this.avatar_consume = avatar_consume;
    }

    public List<MaterialConsume> getAvatar_skill_consume() {
        return avatar_skill_consume;
    }

    public void setAvatar_skill_consume(List<MaterialConsume> avatar_skill_consume) {
        this.avatar_skill_consume = avatar_skill_consume;
    }

    public List<MaterialConsume> getWeapon_consume() {
        return weapon_consume;
    }

    public void setWeapon_consume(List<MaterialConsume> weapon_consume) {
        this.weapon_consume = weapon_consume;
    }
}
