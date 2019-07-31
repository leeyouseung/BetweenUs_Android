package org.techtown.betweenus_android.viewmodel;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import org.techtown.betweenus_android.base.BaseViewModel;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.network.client.ImgUploadClient;
import org.techtown.betweenus_android.network.request.ImgUploadRequest;

import java.io.File;

public class ImgUploadViewModel extends BaseViewModel<Uri> {

    public MutableLiveData<Uri> uri = new MutableLiveData<>();
    public MutableLiveData<File> file = new MutableLiveData<>();

    private ImgUploadClient imgUploadClient;

    public ImgUploadViewModel(Context context) {
        super(context);

        imgUploadClient = new ImgUploadClient();
    }

    public void imgUpload(ImgUploadRequest imgUploadRequest) {
        addDisposable(imgUploadClient.imgUpload(getToken(), imgUploadRequest),getDataObserver());
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(file.getValue().exists())
        {
            file.getValue().delete();
        }
    }
}
