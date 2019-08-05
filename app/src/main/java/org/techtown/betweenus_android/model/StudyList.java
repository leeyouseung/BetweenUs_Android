package org.techtown.betweenus_android.model;

import java.util.List;

public class StudyList {

    private List<Study> foundStudies;

    private List<Study> applyStudies;

    public StudyList(List<Study> foundStudies, List<Study> applyStudies) {
        this.foundStudies = foundStudies;
        this.applyStudies = applyStudies;
    }

    public List<Study> getFoundStudies() {
        return foundStudies;
    }

    public void setFoundStudies(List<Study> foundStudies) {
        this.foundStudies = foundStudies;
    }

    public List<Study> getApplyStudies() {
        return applyStudies;
    }

    public void setApplyStudies(List<Study> applyStudies) {
        this.applyStudies = applyStudies;
    }
}
