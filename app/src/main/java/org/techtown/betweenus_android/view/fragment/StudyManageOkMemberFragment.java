package org.techtown.betweenus_android.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseFragment;
import org.techtown.betweenus_android.databinding.StudyManageMainFragmentBinding;
import org.techtown.betweenus_android.databinding.StudyManageOkMemberFragmentBinding;

public class StudyManageOkMemberFragment extends BaseFragment<StudyManageOkMemberFragmentBinding> {

    public StudyManageOkMemberFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.study_manage_ok_member_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
