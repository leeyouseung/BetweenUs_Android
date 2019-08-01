package org.techtown.betweenus_android.network.client;

import org.techtown.betweenus_android.base.BaseClient;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.network.api.StudyApi;
import org.techtown.betweenus_android.network.request.StudyRequest;

import io.reactivex.Single;
import retrofit2.Response;

public class StudyClient extends BaseClient<StudyApi> {
    @Override
    protected Class api() {
        return StudyApi.class;
    }

    public Single<String> postCreateStudy(Token token, StudyRequest studyRequest) {
        return api.postCreateStudy().map(Response::message);
    }
}
