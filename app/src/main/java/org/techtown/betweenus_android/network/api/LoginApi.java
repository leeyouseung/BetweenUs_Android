package org.techtown.betweenus_android.network.api;

import androidx.annotation.NonNull;

import org.techtown.betweenus_android.model.Login;
import org.techtown.betweenus_android.network.Response;
import org.techtown.betweenus_android.network.request.LoginRequest;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    @NonNull
    @POST("/auth/login")
    Single<retrofit2.Response<Response<Login>>> login(@Body LoginRequest loginRequest);
}
