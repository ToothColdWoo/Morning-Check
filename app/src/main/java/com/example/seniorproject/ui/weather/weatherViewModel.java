package com.example.seniorproject.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class weatherViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public weatherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is weather fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}