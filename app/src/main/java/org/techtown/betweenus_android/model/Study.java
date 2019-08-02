package org.techtown.betweenus_android.model;

public class Study {
    private Integer idx;
    private String memberId;
    private String title;
    private String description;
    private String startTerm;
    private String endTerm;
    private String startTime;
    private String endTime;
    private Integer personnel;
    private Integer status;
    private Integer locationIdx;
    private String location;
    private Integer currentPerson;
    // todo - imgs


    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(String startTerm) {
        this.startTerm = startTerm;
    }

    public String getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(String endTerm) {
        this.endTerm = endTerm;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Integer personnel) {
        this.personnel = personnel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLocationIdx() {
        return locationIdx;
    }

    public void setLocationIdx(Integer locationIdx) {
        this.locationIdx = locationIdx;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Integer currentPerson) {
        this.currentPerson = currentPerson;
    }
}
