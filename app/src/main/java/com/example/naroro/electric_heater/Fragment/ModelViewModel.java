package com.example.naroro.electric_heater.Fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModelViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public ModelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is model fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
