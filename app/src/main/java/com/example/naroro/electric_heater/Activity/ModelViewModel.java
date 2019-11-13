package com.example.naroro.electric_heater.Activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ModelViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public ModelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is nodel fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
