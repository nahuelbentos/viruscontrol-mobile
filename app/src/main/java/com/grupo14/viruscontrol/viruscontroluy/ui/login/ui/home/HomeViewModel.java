package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Viruscontrol.uy");
    }

    public LiveData<String> getText() {
        return mText;
    }
}