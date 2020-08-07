package com.example.mj_talk.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mj_talk.R;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<Integer> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
      //  mText.setValue("snsn");

    }

    public LiveData<Integer> getText() {
        return mText;
    }
}