package org.techtown.betweenus_android.viewmodel;

import android.content.Context;

import org.techtown.betweenus_android.base.BaseViewModel;
import org.techtown.betweenus_android.model.Member;
import org.techtown.betweenus_android.network.client.MemberClient;

import java.util.List;

public class MemberViewModel extends BaseViewModel<List<Member>> {

    private MemberClient memberClient;

    public MemberViewModel(Context context) {
        super(context);
        memberClient = new MemberClient();
    }

    public void getStudyMember(Integer studyIdx) {
        addDisposable(memberClient.getStudyMember(getToken(),studyIdx),getDataObserver());
    }
}
