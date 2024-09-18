package dev.kinodesu.model.request;

public class GenshinUser {
    private String uid;
    private String token;
    private String mid;
    private String name;

    public GenshinUser(String uid, String token, String mid, String name) {
        this.uid = uid;
        this.token = token;
        this.mid = mid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
