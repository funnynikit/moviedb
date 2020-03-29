package com.android.gamechangesproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.gamechangesproject.model.IssuesResponse;
import com.android.gamechangesproject.repository.IssuesRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    IssuesRepository issuesRepository;
    private final static String TAG="MainViewModel";

    public MainViewModel(@NonNull Application application) {
        super(application);

        issuesRepository = new IssuesRepository(application);
    }

    public LiveData<List<IssuesResponse>> getAllIssues()
    {
        return issuesRepository.getMutableMainLiveData();
    }
}
