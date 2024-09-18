package dev.kinodesu.model.response.calculator.item;

import java.util.List;

public class SkillsConsume {
    private List<ConsumeItem> consume_list;
    private SkillInfo skill_info;

    public SkillsConsume() {
    }

    public SkillsConsume(List<ConsumeItem> consume_list, SkillInfo skill_info) {
        this.consume_list = consume_list;
        this.skill_info = skill_info;
    }

    public List<ConsumeItem> getConsume_list() {
        return consume_list;
    }

    public void setConsume_list(List<ConsumeItem> consume_list) {
        this.consume_list = consume_list;
    }

    public SkillInfo getSkill_info() {
        return skill_info;
    }

    public void setSkill_info(SkillInfo skill_info) {
        this.skill_info = skill_info;
    }
}
