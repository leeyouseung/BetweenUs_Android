package org.techtown.betweenus_android.network.client;

import org.techtown.betweenus_android.base.BaseClient;
import org.techtown.betweenus_android.model.Login;
import org.techtown.betweenus_android.network.api.LoginApi;
import org.techtown.betweenus_android.network.request.LoginRequest;

import io.reactivex.Single;

public class LoginClient extends BaseClient<LoginApi> {

    @Override
    protected Class api() {
        return LoginApi.class;
    }

    public Single<Login> login(LoginRequest loginRequest) {
        return api.login(loginRequest).map(getResponseObjectsFunction());
    }
}
