package org.techtown.betweenus_android.viewmodel;

import android.content.Context;

import org.techtown.betweenus_android.base.BaseViewModel;
import org.techtown.betweenus_android.model.Login;
import org.techtown.betweenus_android.network.client.LoginClient;
import org.techtown.betweenus_android.network.request.LoginRequest;

public class LoginViewModel extends BaseViewModel<Login> {

    private LoginClient loginClient;

    public LoginViewModel(Context context) {
        super(context);
        loginClient = new LoginClient();
    }

    public void login(LoginRequest loginRequest) {

        addDisposable(loginClient.login(loginRequest), getDataObserver());
    }
}
