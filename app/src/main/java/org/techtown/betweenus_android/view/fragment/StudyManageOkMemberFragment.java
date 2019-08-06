package org.techtown.betweenus_android.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseFragment;
import org.techtown.betweenus_android.databinding.StudyManageMainFragmentBinding;
import org.techtown.betweenus_android.databinding.StudyManageOkMemberFragmentBinding;
import org.techtown.betweenus_android.manager.CurrentUser;
import org.techtown.betweenus_android.manager.StudyManager;
import org.techtown.betweenus_android.model.Member;

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

        checkStudy();
    }

    private void checkStudy() {
        if (new StudyManager(getContext()).getStudyManager()) {
            
        }
        else {
            view = inflater.inflate(R.layout.study_manage_none_member_fragment, container, false);

            view.findViewById(R.id.content).setOnClickListener(v -> {
                new StudyManager(getContext()).setStudyManager(true);
                getFragmentManager().beginTransaction().detach(StudyManageOkMemberFragment.this).attach(StudyManageOkMemberFragment.this).commit();
            });
        }
    }
}
