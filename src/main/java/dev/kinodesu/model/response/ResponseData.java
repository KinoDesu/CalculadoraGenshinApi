package dev.kinodesu.model.response;

import java.util.List;

public class ResponseData {
    private List<GenshinCharacter> list;
    private int total;

    public ResponseData() {
    }

    public ResponseData(List<GenshinCharacter> list, int total) {
        this.list = list;
        this.total = total;
    }

    public List<GenshinCharacter> getList() {
        return list;
    }

    public void setList(List<GenshinCharacter> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
