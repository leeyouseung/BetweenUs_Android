package org.techtown.betweenus_android.base;

import android.util.Log;

import org.json.JSONObject;
import org.techtown.betweenus_android.network.Response;
import org.techtown.betweenus_android.utils.Utils;

import java.util.Objects;

import io.reactivex.functions.Function;

public abstract class BaseClient<V> {

    protected V api;

    protected abstract Class api();

    public BaseClient() {
        api = (V) Utils.RETROFIT.create(api());
    }

    protected <T> Function<retrofit2.Response<Response<T>>, T> getResponseObjectsFunction() {
        return response -> {
                    if (!response.isSuccessful()) {
                        JSONObject errorBody = new JSONObject(Objects
                                .requireNonNull(
                                        response.errorBody()).string());
                        Log.e("message", errorBody.getString("message"));
                        throw new Exception(errorBody.getString("message"));
                    }
                    Log.e("message", response.body().getStatus() + "");
                    return (T) response.body().getData();
                };
    }

}
