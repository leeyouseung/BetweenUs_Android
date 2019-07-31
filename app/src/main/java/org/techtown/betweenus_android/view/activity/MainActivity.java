package org.techtown.betweenus_android.view.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.MainActivityBinding;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity<MainActivityBinding> {

    private MainViewModel mainViewModel;

    @Override
    protected int layoutId() {
        return R.layout.main_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
    }

    private void initViewModel() {

        mainViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(MainViewModel.class);
    }
}
