package org.techtown.betweenus_android.network.api;

import org.techtown.betweenus_android.model.Location;
import org.techtown.betweenus_android.network.Response;

import java.util.List;

import io.reactivex.Single;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PlaceApi {
    String URL = "/location";

    @GET(URL)
    Single<retrofit2.Response<Response<List<Location>>>> getLocation(@Header("x-access-token") String token);

    @POST(URL)
    Single<retrofit2.Response<Response>> postLocation();

    @PUT(URL)
    Single<retrofit2.Response<Response>> putLocation();

    @DELETE(URL)
    Single<retrofit2.Response<Response>> deleteLocation();
}
