package dev.kinodesu.model.response.calculator.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties()
public class ConsumeItem {
    private long id;
    private String name;
    private String icon;
    private int num;
    private String wiki_url;
    private int level;
    private String icon_url;
    private int lack_num;

    public ConsumeItem() {
    }

    public ConsumeItem(long id, String name, String icon, int num, String wiki_url, int level, String icon_url, int lack_num) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.num = num;
        this.wiki_url = wiki_url;
        this.level = level;
        this.icon_url = icon_url;
        this.lack_num = lack_num;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWiki_url() {
        return wiki_url;
    }

    public void setWiki_url(String wiki_url) {
        this.wiki_url = wiki_url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public int getLack_num() {
        return lack_num;
    }

    public void setLack_num(int lack_num) {
        this.lack_num = lack_num;
    }
}
