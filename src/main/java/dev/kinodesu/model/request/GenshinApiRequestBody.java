package dev.kinodesu.model.request;

import java.util.List;

public class GenshinApiRequestBody {

    private String uid;
    private String region;
    private String keywords;
    private List<Integer> element_attr_ids;
    private List<Integer> weapon_cat_ids;
    private int page;
    private int size;
    private String lang;

    public GenshinApiRequestBody() {
    }

    private GenshinApiRequestBody(Builder builder) {
        this.uid = builder.uid;
        this.region = builder.region;
        this.keywords = builder.keywords;
        this.element_attr_ids = builder.element_attr_ids;
        this.weapon_cat_ids = builder.weapon_cat_ids;
        this.page = builder.page;
        this.size = builder.size;
        this.lang = builder.lang;
    }

    public GenshinApiRequestBody(String uid, String region, String keywords, List<Integer> element_attr_ids,
                                 List<Integer> weapon_cat_ids, int page, int size, String lang) {
        this.uid = uid;
        this.region = region;
        this.keywords = keywords;
        this.element_attr_ids = element_attr_ids;
        this.weapon_cat_ids = weapon_cat_ids;
        this.page = page;
        this.size = size;
        this.lang = lang;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<Integer> getElement_attr_ids() {
        return element_attr_ids;
    }

    public void setElement_attr_ids(List<Integer> element_attr_ids) {
        this.element_attr_ids = element_attr_ids;
    }

    public List<Integer> getWeapon_cat_ids() {
        return weapon_cat_ids;
    }

    public void setWeapon_cat_ids(List<Integer> weapon_cat_ids) {
        this.weapon_cat_ids = weapon_cat_ids;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public static class Builder {
        private String uid;
        private String region;
        private String keywords;
        private List<Integer> element_attr_ids;
        private List<Integer> weapon_cat_ids;
        private int page;
        private int size;
        private String lang;

        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }
        public Builder keywords(String keywords) {
            this.keywords = keywords;
            return this;
        }

        public Builder element_attr_ids(List<Integer> element_attr_ids) {
            this.element_attr_ids = element_attr_ids;
            return this;
        }

        public Builder weapon_cat_ids(List<Integer> weapon_cat_ids) {
            this.weapon_cat_ids = weapon_cat_ids;
            return this;
        }

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder lang(String lang) {
            this.lang = lang;
            return this;
        }

        public GenshinApiRequestBody build() {
            return new GenshinApiRequestBody(this);
        }
    }
}
