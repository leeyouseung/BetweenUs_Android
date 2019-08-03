package org.techtown.betweenus_android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.LoginActivityBinding;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.network.request.LoginRequest;
import org.techtown.betweenus_android.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity<LoginActivityBinding> {

    private LoginViewModel loginViewModel;

    private boolean checkBlind = false;

    @Override
    protected int layoutId() {
        return R.layout.login_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        loginViewModel.getData().observe(this, login -> {
            new Token(this).setToken(login.getToken());
            Toast.makeText(this,"로그인 성공",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        });

        loginViewModel.getErrorMessage().observe(this, message -> Toast.makeText(this,message,Toast.LENGTH_LONG).show());

        clickEvent();
    }

    private void initViewModel() {
        loginViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(LoginViewModel.class);
    }

    private void clickEvent() {

        binding.clear.setOnClickListener(v -> binding.passwordText.setText(""));

        binding.blind.setOnClickListener(v -> {
            if (checkBlind) {
                binding.blind.setImageResource(R.drawable.ic_blind);
                binding.passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                checkBlind = false;
            }
            else {
                binding.blind.setImageResource(R.drawable.ic_blind_check);
                binding.passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                checkBlind = true;
            }
        });

        binding.loginBtn.setOnClickListener(v -> {
            if (binding.idText.getText().toString().isEmpty()) {
                Toast.makeText(this,"아이디를 입력해 주세요",Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.passwordText.getText().toString().isEmpty()) {
                Toast.makeText(this,"비밀번호를 입력해 주세요",Toast.LENGTH_SHORT).show();
                return;
            }
            loginViewModel.login(
                    new LoginRequest(binding.idText.getText().toString(),
                            binding.passwordText.getText().toString()));
        });

        binding.signupBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });
    }
}
