package dev.kinodesu.model.response.calculator.item;

import java.util.List;

public class CalculatorCalendar {
    private String dungeon_name;
    private List<String> drop_day;
    private String calendar_link;
    private boolean has_data;

    public CalculatorCalendar() {
    }

    public CalculatorCalendar(String dungeon_name, List<String> drop_day, String calendar_link, boolean has_data) {
        this.dungeon_name = dungeon_name;
        this.drop_day = drop_day;
        this.calendar_link = calendar_link;
        this.has_data = has_data;
    }

    public String getDungeon_name() {
        return dungeon_name;
    }

    public void setDungeon_name(String dungeon_name) {
        this.dungeon_name = dungeon_name;
    }

    public List<String> getDrop_day() {
        return drop_day;
    }

    public void setDrop_day(List<String> drop_day) {
        this.drop_day = drop_day;
    }

    public String getCalendar_link() {
        return calendar_link;
    }

    public void setCalendar_link(String calendar_link) {
        this.calendar_link = calendar_link;
    }

    public boolean isHas_data() {
        return has_data;
    }

    public void setHas_data(boolean has_data) {
        this.has_data = has_data;
    }
}
