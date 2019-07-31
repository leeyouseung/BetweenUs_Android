package org.techtown.betweenus_android.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import org.techtown.betweenus_android.manager.ViewModelFactory;

/**
 * @author 우주 최강 천재 건우
 */
public abstract class BaseFragment<VB extends ViewDataBinding> extends Fragment {

    protected VB binding;
    protected View view;

    @LayoutRes
    protected abstract int layoutId();

    public static<T extends Fragment> T newInstance(T fragment) {
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, layoutId(), container, false);

        view = binding.getRoot();

        return view;
    }
}
