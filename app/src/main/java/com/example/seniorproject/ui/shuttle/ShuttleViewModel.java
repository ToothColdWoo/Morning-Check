package com.example.seniorproject.ui.shuttle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShuttleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShuttleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}