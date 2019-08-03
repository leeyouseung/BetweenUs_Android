package org.techtown.betweenus_android.network.client;

import org.techtown.betweenus_android.base.BaseClient;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.network.api.PlaceApi;

import java.util.List;

import io.reactivex.Single;

public class PlaceClient extends BaseClient<PlaceApi> {

    @Override
    protected Class api() {
        return PlaceApi.class;
    }

    public Single<List<String>> getLocation(Token token) {
        return api.getLocation(token.getToken()).map(getResponseObjectsFunction());
    }
}
