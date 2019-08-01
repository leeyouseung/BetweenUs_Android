package org.techtown.betweenus_android.network.api;

import org.techtown.betweenus_android.network.Response;

import io.reactivex.Single;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudyApi {

    @GET("/study")
    Single<retrofit2.Response<Response>> getStudyList();

    @POST("/study")
    Single<retrofit2.Response<Response>> postCreateStudy();

    @GET("/study/me")
    Single<retrofit2.Response<Response>> getMyStudy();

    @POST("/study/apply")
    Single<retrofit2.Response<Response>> postCreateStudyApply();

    @DELETE("/study/apply")
    Single<retrofit2.Response<Response>> deleteStudyMember();

    @GET("/study/past")
    Single<retrofit2.Response<Response>> getStudyingList();

    @POST("/study/past")
    Single<retrofit2.Response<Response>> postStudyChangeStatus();
}
