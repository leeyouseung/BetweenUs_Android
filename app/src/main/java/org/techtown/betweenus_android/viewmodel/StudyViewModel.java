package org.techtown.betweenus_android.viewmodel;

import android.content.Context;

import org.techtown.betweenus_android.base.BaseViewModel;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.network.client.StudyClient;
import org.techtown.betweenus_android.network.request.StudyRequest;

import java.util.ArrayList;
import java.util.List;

public class StudyViewModel extends BaseViewModel<List<Study>> {

    public Integer locationIdx;

    private StudyClient studyClient;

    public StudyViewModel(Context context) {
        super(context);
        studyClient = new StudyClient();
    }

    public void getStudyList() {
        addDisposable(studyClient.getStudyList(getToken()),getDataObserver());
    }

    public void postCreateStudy(StudyRequest studyRequest) {
        addDisposable(studyClient.postCreateStudy(getToken(), studyRequest), getBaseObserver());
    }
}
