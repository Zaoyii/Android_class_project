package com.zcyi.classproject11_14;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    public MutableLiveData<String> text;

    public MutableLiveData<String> getText() {
        return text;
    }

    public void setText(MutableLiveData<String> text) {
        this.text = text;
    }
}
