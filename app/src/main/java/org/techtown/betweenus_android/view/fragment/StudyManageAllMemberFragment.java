package org.techtown.betweenus_android.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseFragment;
import org.techtown.betweenus_android.databinding.StudyManageAllMemberFragmentBinding;
import org.techtown.betweenus_android.databinding.StudyManageMainFragmentBinding;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.viewmodel.MemberViewModel;
import org.techtown.betweenus_android.widget.recyclerview.adapter.MemberAdapter;
import org.techtown.betweenus_android.widget.recyclerview.adapter.StudyListAdapter;

public class StudyManageAllMemberFragment extends BaseFragment<StudyManageAllMemberFragmentBinding> {

    private AppCompatActivity view;

    private Integer studyIdx;

    private MemberViewModel memberViewModel;

    public StudyManageAllMemberFragment(Integer studyIdx, AppCompatActivity view) {
        this.studyIdx = studyIdx;
        this.view = view;
    }

    @Override
    protected int layoutId() {
        return R.layout.study_manage_all_member_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();

        memberViewModel.getStudyMember(studyIdx);

        memberViewModel.getData().observe(this, members -> binding.allMemberRecyclerView.setAdapter(new MemberAdapter(members,getContext(), view)));
    }

    private void initViewModel() {
        memberViewModel = ViewModelProviders.of(this, new ViewModelFactory(getContext())).get(MemberViewModel.class);
    }
}
