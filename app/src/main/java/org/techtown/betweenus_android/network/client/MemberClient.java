package org.techtown.betweenus_android.network.client;

import org.techtown.betweenus_android.base.BaseClient;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.model.Member;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.model.StudyList;
import org.techtown.betweenus_android.network.api.MemberApi;
import org.techtown.betweenus_android.network.api.StudyApi;
import org.techtown.betweenus_android.network.request.StudyApplyRequest;
import org.techtown.betweenus_android.network.request.StudyRequest;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public class MemberClient extends BaseClient<MemberApi> {
    @Override
    protected Class api() {
        return MemberApi.class;
    }

    public Single<List<Member>> getStudyMember(Token token, Integer studyIdx) {
        return api.getStudyMember(token.getToken(),studyIdx).map(getResponseObjectsFunction());
    }
}
