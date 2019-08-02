package org.techtown.betweenus_android.view.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.MainActivityBinding;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.viewmodel.MainViewModel;
import org.techtown.betweenus_android.widget.recyclerview.adapter.MainListAdapter;

import java.util.List;


public class MainActivity extends BaseActivity<MainActivityBinding> {

    private List<Study> studies;
    private MainViewModel mainViewModel;
    MainListAdapter mainListAdapter = new MainListAdapter(studies, this);

    // List 에 넣기 위한 index 선언
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
        binding.studyRecyclerView.setAdapter(new MainListAdapter(studies, this));
        initScrollListener();
    }

    private void initViewModel() {
        mainViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(MainViewModel.class);
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

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == lostFoundList.size() - 1) {
                        if (index % 10 == 0) {
                            loadMore();
                        }
                        isLoading = true;
                    }
                }
            }
        });
    }

    // 무한 스크롤 기능 구현
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
}
