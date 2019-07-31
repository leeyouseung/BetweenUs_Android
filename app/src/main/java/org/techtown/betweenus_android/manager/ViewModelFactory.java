package org.techtown.betweenus_android.manager;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.techtown.betweenus_android.viewmodel.ImgUploadViewModel;
import org.techtown.betweenus_android.viewmodel.LoginViewModel;
import org.techtown.betweenus_android.viewmodel.MainViewModel;
import org.techtown.betweenus_android.viewmodel.SignupViewModel;

/**
 * @author 우주 최강 천재 건우
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
        } else {
            return super.create(modelClass);
        }
    }

}
