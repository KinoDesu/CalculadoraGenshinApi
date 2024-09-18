package dev.kinodesu.model.response;

public class GenshinReliquary {
    private int id;
    private String name;
    private String icon;
    private int reliquary_cat_id;
    private int reliquary_level;
    private int level_current;
    private int max_level;

    public GenshinReliquary() {
    }

    public GenshinReliquary(int id, String name, String icon, int reliquary_cat_id, int reliquary_level, int level_current, int max_level) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.reliquary_cat_id = reliquary_cat_id;
        this.reliquary_level = reliquary_level;
        this.level_current = level_current;
        this.max_level = max_level;
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

    public int getReliquary_cat_id() {
        return reliquary_cat_id;
    }

    public void setReliquary_cat_id(int reliquary_cat_id) {
        this.reliquary_cat_id = reliquary_cat_id;
    }

    public int getReliquary_level() {
        return reliquary_level;
    }

    public void setReliquary_level(int reliquary_level) {
        this.reliquary_level = reliquary_level;
    }

    public int getLevel_current() {
        return level_current;
    }

    public void setLevel_current(int level_current) {
        this.level_current = level_current;
    }

    public int getMax_level() {
        return max_level;
    }

    public void setMax_level(int max_level) {
        this.max_level = max_level;
    }
}
