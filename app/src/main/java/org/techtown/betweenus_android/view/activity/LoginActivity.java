package org.techtown.betweenus_android.view.activity;

import android.os.Bundle;
import android.widget.Toast;

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

        viewModel.getData().observe(this, login -> {
            new Token(this).setToken(login.getToken());
            Toast.makeText(this,"로그인 성공",Toast.LENGTH_LONG).show();
        });

        viewModel.getErrorMessage().observe(this, message -> Toast.makeText(this,message,Toast.LENGTH_LONG).show());

        binding.loginBtn.setOnClickListener(v -> {
            if (binding.idText.getText().toString() == null) {
                Toast.makeText(this,"아이디를 입력해 주세요",Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.passwordText.getText().toString() == null) {
                Toast.makeText(this,"비밀번호를 입력해 주세요",Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.login(
                    new LoginRequest(binding.idText.getText().toString(),
                            binding.passwordText.getText().toString()));
        });
    }
}
