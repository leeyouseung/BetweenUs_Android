package org.techtown.betweenus_android.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseFragment;
import org.techtown.betweenus_android.databinding.StudyManageMainFragmentBinding;
import org.techtown.betweenus_android.model.Study;

public class StudyManageMainFragment extends BaseFragment<StudyManageMainFragmentBinding> {

    private Study study;

    public StudyManageMainFragment(Study study) {
        this.study = study;
    }

    @Override
    protected int layoutId() {
        return R.layout.study_manage_main_fragment;
    }

    @Override
    protected Fragment currentFragment() {
        return StudyManageMainFragment.this;
    }

    @Override
    protected Integer studyIdx() {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initText();
    }

    private void initText() {
        binding.studyTitle.setSelected(true);
        binding.startTermText.setText(study.getStartTerm().split(" ")[0]);
        binding.endTermText.setText(study.getEndTerm().split(" ")[0]);
        binding.startTimeText.setText(study.getStartTime());
        binding.endTimeText.setText(study.getEndTime());
        binding.personnelText.setText(study.getCurrentPerson() + " / " + study.getPersonnel());
        binding.placeText.setText(study.getLocation());
        binding.studyTitle.setText(study.getTitle());
        binding.studyDescription.setText(study.getDescription());
        if (!study.getImgs().isEmpty()) {
            Glide.with(this).load(study.getImgs().get(0)).into(binding.studyImageview);
        }
    }

}
