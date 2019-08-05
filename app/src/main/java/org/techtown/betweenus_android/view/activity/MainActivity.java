package org.techtown.betweenus_android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.MainActivityBinding;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.viewmodel.MainViewModel;
import org.techtown.betweenus_android.viewmodel.StudyViewModel;
import org.techtown.betweenus_android.widget.recyclerview.adapter.MainListAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<MainActivityBinding> {

    private List<Study> studies = new ArrayList<>();
    private MainViewModel mainViewModel;
    private StudyViewModel studyViewModel;
    MainListAdapter mainListAdapter = new MainListAdapter(studies, this, this);

    int index = 0;
    boolean isLoading = false;

    @Override
    protected int layoutId() {
        return R.layout.main_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        studyViewModel.getStudyList();

        studyViewModel.getData().observe(this, studyList -> {
            studies = studyList;
            recyclerview();
        });

        clickEvent();
    }

    private void initViewModel() {
        mainViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(MainViewModel.class);
        studyViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(StudyViewModel.class);
    }

    private void recyclerview() {
        binding.studyRecyclerView.setAdapter(new MainListAdapter(studies, this, this));
        initScrollListener();
    }

    private void initScrollListener() {

        binding.studyRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (dy > 0) {
                    binding.create.setVisibility(View.INVISIBLE);
                }
                else {
                    binding.create.setVisibility(View.VISIBLE);
                }
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == studies.size() - 1) {
                        if (index % 10 == 0) {
                            loadMore();
                        }
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        studies.add(null);
        mainListAdapter.notifyItemInserted(studies.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed( ()-> {

            studies.remove(studies.size() - 1);
            int scrollPosition = studies.size();
            mainListAdapter.notifyItemRemoved(scrollPosition);

            isLoading = false;

        }, 1000);
    }

    private void clickEvent() {

        binding.create.setOnClickListener(v -> startActivity(new Intent(this, StudyWriteActivity.class)));

        binding.menuBtn.setOnClickListener(v -> binding.main.openDrawer(GravityCompat.START));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.menu_found:
//                intent = new Intent(this, .class);
//                startActivity(intent);
                break;
            case R.id.menu_apply:
//                intent = new Intent(this, .class);
//                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "문제가 발생하였습니다", Toast.LENGTH_SHORT).show();
        }

        return false;
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
}
