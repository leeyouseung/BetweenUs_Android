package org.techtown.betweenus_android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.TeacherActivityBinding;
import org.techtown.betweenus_android.manager.CurrentUser;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.viewmodel.MemberViewModel;
import org.techtown.betweenus_android.viewmodel.StudyViewModel;
import org.techtown.betweenus_android.widget.recyclerview.adapter.MainListAdapter;
import org.techtown.betweenus_android.widget.recyclerview.adapter.TeacherStudyAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends BaseActivity<TeacherActivityBinding> implements NavigationView.OnNavigationItemSelectedListener {

    private List<Study> studies = new ArrayList<>();

    private StudyViewModel studyViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        binding.teacherNavView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        studyViewModel.getStudyList();

        studyViewModel.getData().observe(this, studyList -> {
            studies = studyList;
            binding.teacherStudyRecyclerView.setAdapter(new TeacherStudyAdapter(studies, this, this));
        });

        binding.teacherMainMenuBtn.setOnClickListener(v -> binding.teacherMain.openDrawer(GravityCompat.START);
    }

    @Override
    protected int layoutId() {
        return R.layout.teacher_activity;
    }

    private void initViewModel() {

        studyViewModel = new StudyViewModel(this);
    }

    @Override
    public void onBackPressed() {

        if (binding.teacherMain.isDrawerOpen(GravityCompat.START)) {
            binding.teacherMain.closeDrawers();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.menu_home:
                intent = new Intent(this, TeacherActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_logout:
                new CurrentUser(this,"betweenUs.db",null,2).delete();
                new Token(this).setToken("");
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "문제가 발생하였습니다", Toast.LENGTH_SHORT).show();
        }

        overridePendingTransition(0, 0);
        binding.teacherMain.closeDrawers();

        return false;
    }
}
