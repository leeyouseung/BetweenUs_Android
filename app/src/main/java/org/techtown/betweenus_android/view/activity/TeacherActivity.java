package org.techtown.betweenus_android.view.activity;

import android.os.Bundle;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.TeacherActivityBinding;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.viewmodel.MemberViewModel;
import org.techtown.betweenus_android.viewmodel.StudyViewModel;
import org.techtown.betweenus_android.widget.recyclerview.adapter.MainListAdapter;
import org.techtown.betweenus_android.widget.recyclerview.adapter.TeacherStudyAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends BaseActivity<TeacherActivityBinding> {

    private List<Study> studies = new ArrayList<>();

    private StudyViewModel studyViewModel;
    private MemberViewModel memberViewModel;

    TeacherStudyAdapter teacherListAdapter = new TeacherStudyAdapter(studies, this, this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        studyViewModel.getStudyList();

        studyViewModel.getData().observe(this, studyList -> {
            studies = studyList;
            binding.teacherStudyRecyclerView.setAdapter(new TeacherStudyAdapter(studies, this, this));
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.teacher_activity;
    }

    private void initViewModel() {
        studyViewModel = new StudyViewModel(this);
        memberViewModel = new MemberViewModel(this);
    }
}
