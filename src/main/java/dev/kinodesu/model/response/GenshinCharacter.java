package dev.kinodesu.model.response;

import java.util.List;

public class  GenshinCharacter {
    private int id;
    private String name;
    private String icon;
    private int weapon_cat_id;
    private int avatar_level;
    private int element_attr_id;
    private int max_level;
    private String level_current;
    private List<GenshinSkill> skill_list;
    private GenshinWeapon weapon;
    private List<GenshinReliquary> reliquary_list;
    private String wiki_url;

    public GenshinCharacter(int id, String name, String icon, int weapon_cat_id, int avatar_level, int element_attr_id, int max_level, String level_current, List<GenshinSkill> skill_list, GenshinWeapon weapon, List<GenshinReliquary> reliquary_list, String wiki_url) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.weapon_cat_id = weapon_cat_id;
        this.avatar_level = avatar_level;
        this.element_attr_id = element_attr_id;
        this.max_level = max_level;
        this.level_current = level_current;
        this.skill_list = skill_list;
        this.weapon = weapon;
        this.reliquary_list = reliquary_list;
        this.wiki_url = wiki_url;
    }

    public GenshinCharacter() {
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

    public int getAvatar_level() {
        return avatar_level;
    }

    public void setAvatar_level(int avatar_level) {
        this.avatar_level = avatar_level;
    }

    public int getElement_attr_id() {
        return element_attr_id;
    }

    public void setElement_attr_id(int element_attr_id) {
        this.element_attr_id = element_attr_id;
    }

    public int getMax_level() {
        return max_level;
    }

    public void setMax_level(int max_level) {
        this.max_level = max_level;
    }

    public String getLevel_current() {
        return level_current;
    }

    public void setLevel_current(String level_current) {
        this.level_current = level_current;
    }

    public List<GenshinSkill> getSkill_list() {
        return skill_list;
    }

    public void setSkill_list(List<GenshinSkill> skill_list) {
        this.skill_list = skill_list;
    }

    public GenshinWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(GenshinWeapon weapon) {
        this.weapon = weapon;
    }

    public List<GenshinReliquary> getReliquary_list() {
        return reliquary_list;
    }

    public void setReliquary_list(List<GenshinReliquary> reliquary_list) {
        this.reliquary_list = reliquary_list;
    }

    public String getWiki_url() {
        return wiki_url;
    }

    public void setWiki_url(String wiki_url) {
        this.wiki_url = wiki_url;
    }
}
