package org.techtown.betweenus_android.network.request;

public class StudyApplyRequest {

    private Integer studyIdx;

    public StudyApplyRequest(Integer studyIdx) {
        this.studyIdx = studyIdx;
    }

    public Integer getStudyIdx() {
        return studyIdx;
    }

    public void setStudyIdx(Integer studyIdx) {
        this.studyIdx = studyIdx;
    }
}
