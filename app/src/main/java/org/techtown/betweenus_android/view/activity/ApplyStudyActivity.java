package org.techtown.betweenus_android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.navigation.NavigationView;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.ApplyStudyActivityBinding;
import org.techtown.betweenus_android.databinding.FoundStudyActivityBinding;
import org.techtown.betweenus_android.manager.CurrentUser;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.model.StudyList;
import org.techtown.betweenus_android.viewmodel.ApplyStudyViewModel;
import org.techtown.betweenus_android.widget.recyclerview.adapter.StudyListAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.plugins.RxJavaPlugins;

public class ApplyStudyActivity extends BaseActivity<ApplyStudyActivityBinding> implements NavigationView.OnNavigationItemSelectedListener {

    private ApplyStudyViewModel applyStudyViewModel;

    @Override
    protected int layoutId() {
        return R.layout.apply_study_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        binding.navView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
        binding.studyRecyclerView.setLayoutManager(mGridLayoutManager);

        applyStudyViewModel.getMyStudy();

        applyStudyViewModel.getData().observe(this, studyList -> {

            List<Study> studies = new CopyOnWriteArrayList<>(studyList.getApplyStudies());

            for (Study study: studies) {
                for (Study delete: studyList.getFoundStudies()) {
                    if (study.getIdx().equals(delete.getIdx())) {
                        studies.remove(study);
                    }
                }
            }

            binding.studyRecyclerView.setAdapter(new StudyListAdapter(studies, this, this, 0));
        });

        clickEvent();
    }

    private void initViewModel() {
        applyStudyViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ApplyStudyViewModel.class);
    }

    private void clickEvent() {
        binding.menuBtn.setOnClickListener(v -> binding.main.openDrawer(GravityCompat.START));
    }

    @Override
    public void onBackPressed() {

        if (binding.main.isDrawerOpen(GravityCompat.START)) {
            binding.main.closeDrawers();
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
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_found:
                intent = new Intent(this, FoundStudyActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_apply:
                intent = new Intent(this, ApplyStudyActivity.class);
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
        binding.main.closeDrawers();

        return false;
    }
}
