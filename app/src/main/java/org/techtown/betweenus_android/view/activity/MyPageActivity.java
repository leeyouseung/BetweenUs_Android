package org.techtown.betweenus_android.view.activity;

import android.os.Bundle;
import android.util.Log;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.MypageActivityBinding;
import org.techtown.betweenus_android.manager.CurrentUser;
import org.techtown.betweenus_android.model.Member;

public class MyPageActivity extends BaseActivity<MypageActivityBinding> {
    @Override
    protected int layoutId() {
        return R.layout.mypage_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CurrentUser currentUser = new CurrentUser(this, "betweenUs.db", null, 2);

        Member myInfo = currentUser.getResult();

        Log.d("Info", myInfo.getName());

        binding.nameText.setText(myInfo.getName());
    }
}
