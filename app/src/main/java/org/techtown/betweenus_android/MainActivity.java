package org.techtown.betweenus_android;

import android.os.Bundle;

import org.techtown.betweenus_android.databinding.MainActivityBinding;


public class MainActivity extends BaseActivity<MainActivityBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.main_activity;
    }
}
