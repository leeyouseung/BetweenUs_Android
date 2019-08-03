package org.techtown.betweenus_android.network.client;

import org.json.JSONObject;
import org.techtown.betweenus_android.base.BaseClient;
import org.techtown.betweenus_android.model.Login;
import org.techtown.betweenus_android.network.api.LoginApi;
import org.techtown.betweenus_android.network.request.LoginRequest;

import java.util.Objects;

import io.reactivex.Single;

public class LoginClient extends BaseClient<LoginApi> {

    @Override
    protected Class api() {
        return LoginApi.class;
    }

    public Single<Login> login(LoginRequest loginRequest) {
        return api.login(loginRequest).map(response -> {

            if (!response.isSuccessful()) {
                JSONObject errorBody = new JSONObject(Objects
                        .requireNonNull(
                                response.errorBody()).string());

                throw new Exception(errorBody.getString("message"));
            }

            if (response.body().getStatus() == 200) {

                Login login = new Login();
                login.setToken(response.body().getData().getToken());
                login.setRefreshToken(response.body().getData().getRefreshToken());

                return login;
            }
            else if (response.body().getStatus() == 401) {

                throw new Exception("아이디 또는 비밀번호가 틀렸습니다");
            }
            else {

                throw new Exception(response.body().getMessage());
            }

        });
    }
}
