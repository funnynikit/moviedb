package com.android.insuris.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.android.insuris.model.ResultsItem;
import com.android.insuris.repository.MoviesRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MoviesRepository moviesRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        moviesRepository = new MoviesRepository();
    }

    public LiveData<List<ResultsItem>> getAllIssues()
    {
        return moviesRepository.getMutableMainLiveData();
    }
}
