package org.techtown.betweenus_android.base;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.LayoutRes;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<VB extends ViewDataBinding> extends AppCompatActivity {

    protected VB binding;

    @LayoutRes
    protected abstract int layoutId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, layoutId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation);
        }
    }
}
