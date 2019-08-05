package org.techtown.betweenus_android.viewmodel;

import android.content.Context;

import org.techtown.betweenus_android.base.BaseViewModel;
import org.techtown.betweenus_android.model.StudyList;
import org.techtown.betweenus_android.network.client.StudyClient;

public class ApplyStudyViewModel extends BaseViewModel<StudyList> {

    private StudyClient studyClient;

    public ApplyStudyViewModel(Context context) {
        super(context);
        studyClient = new StudyClient();
    }

    public void getMyStudy() {
        addDisposable(studyClient.getMyStudy(getToken()),getDataObserver());
    }
}
