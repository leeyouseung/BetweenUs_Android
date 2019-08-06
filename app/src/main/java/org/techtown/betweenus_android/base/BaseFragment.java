package org.techtown.betweenus_android.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.manager.StudyManager;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.view.fragment.StudyManageOkMemberFragment;

/**
 * @author 우주 최강 천재 건우
 */
public abstract class BaseFragment<VB extends ViewDataBinding> extends Fragment {

    protected VB binding;
    protected View view;
    protected LayoutInflater inflater;
    protected ViewGroup container;

    @LayoutRes
    protected abstract int layoutId();

    protected abstract Fragment currentFragment();

    protected abstract Integer studyIdx();

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

        this.inflater = inflater;
        this.container = container;
        view = binding.getRoot();
        checkStudy();

        return view;
    }

    private void checkStudy() {

        if (layoutId() == R.layout.study_manage_ok_member_fragment && studyIdx() != null) {
            if (!new StudyManager(getContext()).getStudyManager(studyIdx())) {

                view = inflater.inflate(R.layout.study_manage_none_member_fragment, container, false);

                view.findViewById(R.id.content).setOnClickListener(v -> {

                    new AlertDialog.Builder(getContext())
                            .setMessage("정말 수업을 시작 하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("네",
                                    (dialog, id) -> {
                                        new StudyManager(getContext()).setStudyManager(studyIdx(),true);
                                        getFragmentManager().beginTransaction().detach(currentFragment()).attach(currentFragment()).commit();
                                    })
                            .setNegativeButton("아니요",
                                    (dialog, id) -> {
                                        dialog.cancel();
                                    }).create().show();
                });
            }
        }
    }
}
