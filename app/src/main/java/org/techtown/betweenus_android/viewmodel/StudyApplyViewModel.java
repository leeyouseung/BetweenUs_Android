package org.techtown.betweenus_android.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.base.BaseViewModel;
import org.techtown.betweenus_android.network.client.StudyClient;
import org.techtown.betweenus_android.network.request.StudyApplyRequest;

public class StudyApplyViewModel extends BaseViewModel<String> {

    private StudyClient studyClient;

    public StudyApplyViewModel(Context context) {
        super(context);
        studyClient = new StudyClient();
    }

    public void postCreateApplyStudy(StudyApplyRequest studyApplyRequest) {
        addDisposable(studyClient.postCreateApplyStudy(getToken(), studyApplyRequest), getDataObserver());
    }
}
