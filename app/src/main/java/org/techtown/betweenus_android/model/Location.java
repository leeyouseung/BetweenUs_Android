package org.techtown.betweenus_android.model;

public class Location {

    private Integer idx;
    private String place;
    private Integer status;

    public Location(Integer idx, String place, Integer status) {
        this.idx = idx;
        this.place = place;
        this.status = status;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
