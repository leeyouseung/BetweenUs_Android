package org.techtown.betweenus_android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.base.BaseActivity;
import org.techtown.betweenus_android.databinding.StudyManageActivityBinding;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.view.fragment.StudyManageAllMemberFragment;
import org.techtown.betweenus_android.view.fragment.StudyManageMainFragment;
import org.techtown.betweenus_android.view.fragment.StudyManageOkMemberFragment;
import org.techtown.betweenus_android.viewmodel.StudyManageViewModel;

public class StudyManageActivity extends BaseActivity<StudyManageActivityBinding> {

    private FragmentManager fm;
    private FragmentTransaction tran;

    private Study study;

    private StudyManageViewModel studyManageViewModel;

    @Override
    protected int layoutId() {
        return R.layout.study_manage_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        initIntent();
        setFrag(0);

        clickEvent();
    }

    private void initViewModel() {
        studyManageViewModel = ViewModelProviders.of(this,new ViewModelFactory(this)).get(StudyManageViewModel.class);
    }

    private void initIntent() {
        Intent intent = getIntent();
        study = (Study) intent.getSerializableExtra("study");
    }

    private void clickEvent() {

        binding.home.setOnClickListener(v -> {
            setFrag(0);
            changeBtn(0);
        });

        binding.allMember.setOnClickListener(v -> {
            setFrag(1);
            changeBtn(1);
        });

        binding.okMember.setOnClickListener(v -> {
            setFrag(2);
            changeBtn(2);
        });

        binding.scan.setOnClickListener(v -> new IntentIntegrator(this).initiateScan());

        binding.back.setOnClickListener(v -> finish());
    }

    public void setFrag(int n){
        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        switch (n){
            case 0:
                tran.replace(R.id.frame, new StudyManageMainFragment(study));
                tran.commit();
                break;
            case 1:
                tran.replace(R.id.frame, new StudyManageAllMemberFragment(study.getIdx(),this));
                tran.commit();
                break;
            case 2:
                tran.replace(R.id.frame, new StudyManageOkMemberFragment());
                tran.commit();
                break;
        }
    }

    private void changeBtn(int n) {

        switch (n){
            case 0:
                binding.home.setImageResource(R.drawable.ic_home_black_24dp);
                binding.allMember.setImageResource(R.drawable.ic_group_none_24dp);
                binding.okMember.setImageResource(R.drawable.ic_person_add_none_24dp);
                break;
            case 1:
                binding.home.setImageResource(R.drawable.ic_home_none_24dp);
                binding.allMember.setImageResource(R.drawable.ic_group_black_24dp);
                binding.okMember.setImageResource(R.drawable.ic_person_add_none_24dp);
                break;
            case 2:
                binding.home.setImageResource(R.drawable.ic_home_none_24dp);
                binding.allMember.setImageResource(R.drawable.ic_group_none_24dp);
                binding.okMember.setImageResource(R.drawable.ic_person_add_black_24dp);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result != null) {
            String contents = result.getContents();
            if(contents != null) {
                Toast.makeText(this,"스캔 완료",Toast.LENGTH_LONG).show();
                new IntentIntegrator(this).initiateScan();
            }
            else {
                return;
            }
        }
        else {
            return;
        }
    }
}
