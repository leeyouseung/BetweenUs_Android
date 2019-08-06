package org.techtown.betweenus_android.view.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseFragment;
import org.techtown.betweenus_android.databinding.StudyManageMainFragmentBinding;
import org.techtown.betweenus_android.databinding.StudyManageOkMemberFragmentBinding;
import org.techtown.betweenus_android.manager.CurrentUser;
import org.techtown.betweenus_android.manager.StudyManager;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.model.Member;
import org.techtown.betweenus_android.viewmodel.MemberViewModel;
import org.techtown.betweenus_android.viewmodel.StudyViewModel;
import org.techtown.betweenus_android.widget.recyclerview.adapter.MemberAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudyManageOkMemberFragment extends BaseFragment<StudyManageOkMemberFragmentBinding> {

    private MemberViewModel memberViewModel;
    private StudyViewModel studyViewModel;

    private AppCompatActivity view;
    private Integer studyIdx;

    public StudyManageOkMemberFragment(Integer studyIdx, AppCompatActivity view) {
        this.studyIdx = studyIdx;
        this.view = view;
    }
    @Override
    protected int layoutId() {
        return R.layout.study_manage_ok_member_fragment;
    }

    @Override
    protected Fragment currentFragment() {
        return StudyManageOkMemberFragment.this;
    }

    @Override
    protected Integer studyIdx() {
        return studyIdx;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
        memberViewModel.getStudyMember(studyIdx);

        memberViewModel.getData().observe(this, members -> {
            List<Member> memberList = new ArrayList<>();

            for (Member member: members) {
                if (member.getStatus() == 1) {
                    memberList.add(member);
                }
            }

            binding.okMemberRecyclerView.setAdapter(new MemberAdapter(memberList,getContext(), view));
        });

        clickEvent();
    }

    private void initViewModel() {
        memberViewModel = ViewModelProviders.of(this, new ViewModelFactory(getContext())).get(MemberViewModel.class);
        studyViewModel = ViewModelProviders.of(this, new ViewModelFactory(getContext())).get(StudyViewModel.class);
    }

    private void clickEvent() {
        binding.end.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setMessage("정말 수업을 종료 하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("네",
                            (dialog, id) -> {
                                studyViewModel.postStudyEnd(studyIdx);
                                new StudyManager(getContext()).setStudyManager(studyIdx,false);
                                getFragmentManager().beginTransaction().detach(currentFragment()).attach(currentFragment()).commit();
                            })
                    .setNegativeButton("아니요",
                            (dialog, id) -> {
                                dialog.cancel();
                            }).create().show();
        });
    }
}
