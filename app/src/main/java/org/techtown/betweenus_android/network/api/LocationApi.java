package org.techtown.betweenus_android.network.api;

import org.techtown.betweenus_android.network.Response;

import io.reactivex.Single;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface LocationApi {
    String URL = "/location";

    @GET(URL)
    Single<retrofit2.Response<Response>> getLocation();

    @POST(URL)
    Single<retrofit2.Response<Response>> postLocation();

    @PUT(URL)
    Single<retrofit2.Response<Response>> putLocation();

    @DELETE(URL)
    Single<retrofit2.Response<Response>> deleteLocation();
}
