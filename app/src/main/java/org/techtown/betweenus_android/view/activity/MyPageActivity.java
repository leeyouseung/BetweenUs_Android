package org.techtown.betweenus_android.view.activity;

import android.os.Bundle;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.MypageActivityBinding;

public class MyPageActivity extends BaseActivity<MypageActivityBinding> {
    @Override
    protected int layoutId() {
        return R.layout.mypage_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
