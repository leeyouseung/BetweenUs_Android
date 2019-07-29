package org.techtown.betweenus_android.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import org.techtown.betweenus_android.manager.ViewModelFactory;

public abstract class BaseActivity<VB extends ViewDataBinding, T extends ViewModel> extends AppCompatActivity {

    protected VB binding;
    protected T viewModel;

    @LayoutRes
    protected abstract int layoutId();

    protected abstract Class viewModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, layoutId());
        viewModel = (T) ViewModelProviders.of(this, new ViewModelFactory(this)).get(viewModel());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }
}
