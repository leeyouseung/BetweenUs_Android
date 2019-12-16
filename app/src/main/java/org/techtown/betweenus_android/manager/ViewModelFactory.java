package org.techtown.betweenus_android.manager;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.techtown.betweenus_android.viewmodel.ApplyStudyViewModel;
import org.techtown.betweenus_android.viewmodel.ImgUploadViewModel;
import org.techtown.betweenus_android.viewmodel.LoginViewModel;
import org.techtown.betweenus_android.viewmodel.MainViewModel;
import org.techtown.betweenus_android.viewmodel.MemberViewModel;
import org.techtown.betweenus_android.viewmodel.MyPageViewModel;
import org.techtown.betweenus_android.viewmodel.PlaceViewModel;
import org.techtown.betweenus_android.viewmodel.SignupViewModel;
import org.techtown.betweenus_android.viewmodel.StudyApplyViewModel;
import org.techtown.betweenus_android.viewmodel.StudyViewModel;

/**
 * ViewModel 추가 시킬때 마다 create에 추가 시켜 줘야됨
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass == LoginViewModel.class) {
            return (T) new LoginViewModel(context);
        } else if (modelClass == MainViewModel.class) {
            return (T) new MainViewModel(context);
        } else if (modelClass == SignupViewModel.class) {
            return (T) new SignupViewModel(context);
        } else if (modelClass == ImgUploadViewModel.class) {
            return (T) new ImgUploadViewModel(context);
        } else if (modelClass == StudyViewModel.class) {
            return (T) new StudyViewModel(context);
        } else if (modelClass == PlaceViewModel.class) {
            return (T) new PlaceViewModel(context);
        } else if (modelClass == StudyApplyViewModel.class) {
            return (T) new StudyApplyViewModel(context);
        } else if (modelClass == ApplyStudyViewModel.class) {
            return (T) new ApplyStudyViewModel(context);
        } else if (modelClass == MemberViewModel.class) {
            return (T) new MemberViewModel(context);
        } else if (modelClass == MyPageViewModel.class) {
            return (T) new MyPageViewModel(context);
        } else {
            return super.create(modelClass);
        }
    }

}
