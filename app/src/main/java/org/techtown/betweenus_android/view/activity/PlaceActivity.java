package org.techtown.betweenus_android.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.PlaceActivityBinding;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.viewmodel.PlaceViewModel;
import org.techtown.betweenus_android.widget.recyclerview.adapter.MainListAdapter;
import org.techtown.betweenus_android.widget.recyclerview.adapter.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlaceActivity extends BaseActivity<PlaceActivityBinding> {

    private PlaceViewModel placeViewModel;

    @Override
    protected int layoutId() {
        return R.layout.place_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        placeViewModel.getLocation();

        placeViewModel.getData().observe(this, places -> {
            binding.placeRecyclerView.setAdapter(new PlaceAdapter(places, this));
        });

        clickEvent();
    }

    private void initViewModel() {
        placeViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(PlaceViewModel.class);
    }

    private void clickEvent() {
        binding.check.setOnClickListener(v -> {
            Intent intent = new Intent(this, StudyWriteActivity.class);
            startActivity(intent);
        });
    }
}
