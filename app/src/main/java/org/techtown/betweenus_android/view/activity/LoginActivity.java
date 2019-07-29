package org.techtown.betweenus_android.view.activity;

import android.os.Bundle;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.LoginActivityBinding;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.network.request.LoginRequest;
import org.techtown.betweenus_android.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity<LoginActivityBinding, LoginViewModel> {

    @Override
    protected int layoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected Class viewModel() {
        return LoginViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel.getData().observe(this, login -> new Token(this).setToken(login.getToken()));

        binding.loginBtn.setOnClickListener(v -> viewModel.login(
                new LoginRequest(binding.idText.getText().toString(),binding.passwordText.getText().toString())));
    }
}
