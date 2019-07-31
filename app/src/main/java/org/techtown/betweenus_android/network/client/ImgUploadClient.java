package org.techtown.betweenus_android.network.client;

import android.net.Uri;

import org.techtown.betweenus_android.base.BaseClient;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.network.api.ImgUploadApi;
import org.techtown.betweenus_android.network.request.ImgUploadRequest;

import io.reactivex.Single;

public class ImgUploadClient extends BaseClient<ImgUploadApi> {

    @Override
    protected Class api() {
        return ImgUploadApi.class;
    }

    public Single<Uri> imgUpload(Token token, ImgUploadRequest imgUploadRequest) {

        return api.imgUpload(token.getToken(), imgUploadRequest.getFile()).map(getResponseObjectsFunction());
    }
}
