package org.techtown.betweenus_android.network.request;

import java.io.Serializable;

import okhttp3.MultipartBody;

public class ImgUploadRequest implements Serializable {

    private MultipartBody.Part file;

    public ImgUploadRequest() {

    }

    public ImgUploadRequest(MultipartBody.Part file) {
        this.file = file;
    }

    public MultipartBody.Part getFile() {
        return file;
    }

    public void setFile(MultipartBody.Part file) {
        this.file = file;
    }
}
