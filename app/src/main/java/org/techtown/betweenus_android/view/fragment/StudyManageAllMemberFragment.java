package org.techtown.betweenus_android.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseFragment;
import org.techtown.betweenus_android.databinding.StudyManageAllMemberFragmentBinding;
import org.techtown.betweenus_android.databinding.StudyManageMainFragmentBinding;

public class StudyManageAllMemberFragment extends BaseFragment<StudyManageAllMemberFragmentBinding> {

    public StudyManageAllMemberFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.study_manage_all_member_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
