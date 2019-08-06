package org.techtown.betweenus_android.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.TeacherActivityBinding;
import org.techtown.betweenus_android.databinding.TeacherMemberActivityBinding;
import org.techtown.betweenus_android.manager.StudyManager;
import org.techtown.betweenus_android.model.Member;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.viewmodel.MemberViewModel;
import org.techtown.betweenus_android.viewmodel.StudyViewModel;
import org.techtown.betweenus_android.widget.recyclerview.adapter.TeacherMemberAdapter;
import org.techtown.betweenus_android.widget.recyclerview.adapter.TeacherStudyAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeacherMemberActivity extends BaseActivity<TeacherMemberActivityBinding> {

    private List<Member> members = new ArrayList<>();

    private MemberViewModel memberViewModel;

    private Study study;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        initIntent();

        memberViewModel.getStudyMember(study.getIdx());

        memberViewModel.getData().observe(this, members -> {
            binding.teacherAllMemberRecyclerView.setAdapter(new TeacherMemberAdapter(members, this, this));
        });

        binding.back.setOnClickListener(v -> finish());
    }

    @Override
    protected int layoutId() {
        return R.layout.teacher_member_activity;
    }

    private void initViewModel() {

        memberViewModel = new MemberViewModel(this);
    }

    private void initIntent() {
        Intent intent = getIntent();
        study = (Study) intent.getSerializableExtra("study");
    }
}
