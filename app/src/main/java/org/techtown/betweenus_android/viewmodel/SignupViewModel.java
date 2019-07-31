package org.techtown.betweenus_android.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import org.techtown.betweenus_android.base.BaseViewModel;
import org.techtown.betweenus_android.network.client.SignupClient;
import org.techtown.betweenus_android.network.request.SignupRequest;

public class SignupViewModel extends BaseViewModel {

    private SignupClient signupClient;

    public SignupViewModel(Context context) {
        super(context);

        signupClient = new SignupClient();
    }

    public void signup(SignupRequest signupRequest) {
        addDisposable(signupClient.signup(signupRequest),getBaseObserver());
    }
}
