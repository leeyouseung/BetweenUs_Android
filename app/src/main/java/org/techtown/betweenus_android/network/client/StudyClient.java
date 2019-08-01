package org.techtown.betweenus_android.network.client;

import org.techtown.betweenus_android.base.BaseClient;
import org.techtown.betweenus_android.network.api.StudyApi;

import io.reactivex.Single;

public class StudyClient extends BaseClient<StudyApi> {
    @Override
    protected Class api() {
        return StudyApi.class;
    }

    public Single<>
}
