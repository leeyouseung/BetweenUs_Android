package org.techtown.betweenus_android.network.api;

import org.techtown.betweenus_android.network.Response;
import org.techtown.betweenus_android.network.request.SignupRequest;

import io.reactivex.Single;
import retrofit2.http.POST;

public interface SignupApi {

    @POST("")
    Single<retrofit2.Response<Response>> signup(SignupRequest signupRequest);
}
