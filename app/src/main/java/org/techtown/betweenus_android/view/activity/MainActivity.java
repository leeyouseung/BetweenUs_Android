package org.techtown.betweenus_android.view.activity;

import android.os.Bundle;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.MainActivityBinding;
import org.techtown.betweenus_android.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity<MainActivityBinding, MainViewModel> {

    @Override
    protected int layoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected Class viewModel() {
        return MainViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
