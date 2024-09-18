package dev.kinodesu.model.request.calculator;

import java.util.List;

public class CalculatorRequestBody {
    private List<CalculatorRequestCharacter> items;
    private String lang;
    private String region;
    private String uid;

    public CalculatorRequestBody() {
    }

    public CalculatorRequestBody(List<CalculatorRequestCharacter> items, String lang, String region, String uid) {
        this.items = items;
        this.lang = lang;
        this.region = region;
        this.uid = uid;
    }

    public List<CalculatorRequestCharacter> getItems() {
        return items;
    }

    public void setItems(List<CalculatorRequestCharacter> items) {
        this.items = items;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
