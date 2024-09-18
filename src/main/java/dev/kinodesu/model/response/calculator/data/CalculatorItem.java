package dev.kinodesu.model.response.calculator.data;

import dev.kinodesu.model.response.calculator.item.*;

import java.util.List;

public class CalculatorItem {
    private List<ConsumeItem> avatar_consume;
    private List<ConsumeItem> avatar_skill_consume;
    private List<ConsumeItem> weapon_consume;
    private List<ConsumeItem> reliquary_consume;
    private List<SkillsConsume> skills_consume;
    private CalculatorCalendar calendar;
    private String lineup_recommend;

    public CalculatorItem() {
    }

    public CalculatorItem(List<ConsumeItem> avatar_consume, List<ConsumeItem> avatar_skill_consume, List<ConsumeItem> weapon_consume, List<ConsumeItem> reliquary_consume, List<SkillsConsume> skills_consume, CalculatorCalendar calendar, String lineup_recommend) {
        this.avatar_consume = avatar_consume;
        this.avatar_skill_consume = avatar_skill_consume;
        this.weapon_consume = weapon_consume;
        this.reliquary_consume = reliquary_consume;
        this.skills_consume = skills_consume;
        this.calendar = calendar;
        this.lineup_recommend = lineup_recommend;
    }

    public List<ConsumeItem> getAvatar_consume() {
        return avatar_consume;
    }

    public void setAvatar_consume(List<ConsumeItem> avatar_consume) {
        this.avatar_consume = avatar_consume;
    }

    public List<ConsumeItem> getAvatar_skill_consume() {
        return avatar_skill_consume;
    }

    public void setAvatar_skill_consume(List<ConsumeItem> avatar_skill_consume) {
        this.avatar_skill_consume = avatar_skill_consume;
    }

    public List<ConsumeItem> getWeapon_consume() {
        return weapon_consume;
    }

    public void setWeapon_consume(List<ConsumeItem> weapon_consume) {
        this.weapon_consume = weapon_consume;
    }

    public List<ConsumeItem> getReliquary_consume() {
        return reliquary_consume;
    }

    public void setReliquary_consume(List<ConsumeItem> reliquary_consume) {
        this.reliquary_consume = reliquary_consume;
    }

    public List<SkillsConsume> getSkills_consume() {
        return skills_consume;
    }

    public void setSkills_consume(List<SkillsConsume> skills_consume) {
        this.skills_consume = skills_consume;
    }

    public CalculatorCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(CalculatorCalendar calendar) {
        this.calendar = calendar;
    }

    public String getLineup_recommend() {
        return lineup_recommend;
    }

    public void setLineup_recommend(String lineup_recommend) {
        this.lineup_recommend = lineup_recommend;
    }
}
