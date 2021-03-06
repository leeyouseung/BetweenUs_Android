package org.techtown.betweenus_android.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import org.techtown.betweenus_android.base.BaseViewModel;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.network.client.StudyClient;
import org.techtown.betweenus_android.network.request.StudyApplyRequest;
import org.techtown.betweenus_android.network.request.StudyRequest;

import java.util.List;

public class StudyViewModel extends BaseViewModel<List<Study>> {

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

    public void postStudyEnd(Integer studyIdx) {
        addDisposable(studyClient.postStudyEnd(getToken(), studyIdx), getBaseObserver());
    }

    public void postQr(String url) {
        addDisposable(studyClient.postQr(getToken(), url),getBaseObserver());
    }
}
