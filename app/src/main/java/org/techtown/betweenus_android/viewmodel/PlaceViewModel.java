package org.techtown.betweenus_android.viewmodel;

import android.content.Context;

import org.techtown.betweenus_android.base.BaseViewModel;
import org.techtown.betweenus_android.network.client.PlaceClient;

import java.util.List;

public class PlaceViewModel extends BaseViewModel<List<String>> {

    private PlaceClient placeClient;

    public PlaceViewModel(Context context) {
        super(context);
        placeClient = new PlaceClient();
    }

    public void getLocation() {
        addDisposable(placeClient.getLocation(getToken()),getDataObserver());
    }
}
