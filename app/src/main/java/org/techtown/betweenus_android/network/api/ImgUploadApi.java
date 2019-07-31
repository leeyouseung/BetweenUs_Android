package org.techtown.betweenus_android.network.api;

import android.net.Uri;

import org.techtown.betweenus_android.network.Response;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImgUploadApi {

    @Multipart
    @POST("")
    Single<retrofit2.Response<Response<Uri>>> imgUpload(@Part MultipartBody.Part file);
}
