package org.techtown.betweenus_android.network.request;

import java.util.Date;
import java.util.List;

public class StudyRequest {

    private String title;
    private String description;
    private Date startTerm;
    private Date endTerm;
    private Date startTime;
    private Date endTime;
    private Integer personnel;
    private Integer locationIdx;
    private List<String> imgs;

    public StudyRequest(String title, String description, Date startTerm, Date endTerm, Date startTime, Date endTime, Integer personnel, Integer locationIdx, List<String> imgs) {
        this.title = title;
        this.description = description;
        this.startTerm = startTerm;
        this.endTerm = endTerm;
        this.startTime = startTime;
        this.endTime = endTime;
        this.personnel = personnel;
        this.locationIdx = locationIdx;
        this.imgs = imgs;
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

    public Date getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(Date startTerm) {
        this.startTerm = startTerm;
    }

    public Date getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(Date endTerm) {
        this.endTerm = endTerm;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Integer personnel) {
        this.personnel = personnel;
    }

    public Integer getLocationIdx() {
        return locationIdx;
    }

    public void setLocationIdx(Integer locationIdx) {
        this.locationIdx = locationIdx;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

}
