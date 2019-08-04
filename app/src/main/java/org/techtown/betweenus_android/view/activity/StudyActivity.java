package org.techtown.betweenus_android.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.StudyActivityBinding;
import org.techtown.betweenus_android.model.Study;

public class StudyActivity extends BaseActivity<StudyActivityBinding> {

    private Study study;

    @Override
    protected int layoutId() {
        return R.layout.study_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initIntent();

        initText();

        clickEvent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        study = (Study) intent.getSerializableExtra("study");
    }

    private void initText() {
        binding.startTermText.setText(study.getStartTerm().split(" ")[0]);
        binding.endTermText.setText(study.getEndTerm().split(" ")[0]);
        binding.startTimeText.setText(study.getStartTime());
        binding.endTimeText.setText(study.getEndTime());
        binding.memberIdText.setText(study.getMemberId());
        binding.personnelText.setText(study.getCurrentPerson() + " / " + study.getPersonnel());
        binding.placeText.setText(study.getLocation());
        binding.studyTitle.setText(study.getTitle());
        binding.studyDescription.setText(study.getDescription());
        if (!study.getImgs().isEmpty()) {
            Glide.with(this).load(study.getImgs().get(0)).into(binding.studyImageview);
        }
    }

    private void clickEvent() {
        binding.applyBtn.setOnClickListener(v -> {

        });
    }
}
