package org.techtown.betweenus_android.network.client;

import org.json.JSONObject;
import org.techtown.betweenus_android.base.BaseClient;
import org.techtown.betweenus_android.network.api.SignupApi;
import org.techtown.betweenus_android.network.request.SignupRequest;

import java.util.Objects;

import io.reactivex.Single;
import retrofit2.Response;

public class SignupClient extends BaseClient<SignupApi> {

    @Override
    protected Class api() {
        return SignupApi.class;
    }

    public Single<String> signup(SignupRequest signupRequest) {
        return api.signup(signupRequest).map(response -> {

            if (!response.isSuccessful()) {
                JSONObject errorBody = new JSONObject(Objects
                        .requireNonNull(
                                response.errorBody()).string());

                throw new Exception(errorBody.getString("message"));
            }

            if (response.body().getStatus() == 200) {

                return response.body().getMessage();
            }
            else if (response.body().getStatus() == 401) {

                throw new Exception("아이디가 중복되었습니다.");
            }
            else {

                throw new Exception(response.body().getMessage());
            }

        });
    }
}
