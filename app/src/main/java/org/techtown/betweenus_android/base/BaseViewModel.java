package org.techtown.betweenus_android.base;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.model.Login;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseViewModel<T> extends ViewModel {

    private CompositeDisposable disposable;
    private Token token;

    final MutableLiveData<String> successMessage = new MutableLiveData<>();
    final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    final MutableLiveData<T> data = new MutableLiveData<>();

    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }

    public LiveData<T> getData() {
        return data;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public Token getToken() {
        return token;
    }

    protected BaseViewModel(Context context) {
        disposable = new CompositeDisposable();
        token = new Token(context);
    }

    public void addDisposable(Single single, DisposableSingleObserver observer) {
        disposable.add((Disposable) single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer));
    }

    public DisposableSingleObserver<String> getBaseObserver() {
        return new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(String s) {
                successMessage.setValue(s);
            }

            @Override
            public void onError(Throwable e) {
                errorMessage.setValue(e.getMessage());
            }
        };
    }

    public DisposableSingleObserver<T> getDataObserver() {
        return new DisposableSingleObserver<T>() {
            @Override
            public void onSuccess(T t) {
                data.setValue(t);
            }

            @Override
            public void onError(Throwable e) {
                errorMessage.setValue(e.getMessage());
            }
        };
    }
}
