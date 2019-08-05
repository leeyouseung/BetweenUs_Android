package org.techtown.betweenus_android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.SplashActivityBinding;

// 애니메이션 추가된 스플래쉬액티비티
// 건들 ㄴㄴ

public class SplashActivity extends BaseActivity<SplashActivityBinding> {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Animation hold = AnimationUtils.loadAnimation(this, R.anim.splash_hold);

        final Animation scale = AnimationUtils.loadAnimation(this, R.anim.splash_scale);

        hold.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.logoImage.clearAnimation();
                binding.logoImage.startAnimation(scale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.logoImage.clearAnimation();
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.logoImage.startAnimation(hold);

    }

    @Override
    protected int layoutId() {
        return R.layout.splash_activity;
    }
}
