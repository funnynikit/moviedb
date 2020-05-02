package com.android.insuris.repository;

import androidx.lifecycle.MutableLiveData;
import com.android.insuris.di.MyApplication;
import com.android.insuris.model.MoviesData;
import com.android.insuris.model.ResultsItem;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private static final String TAG = "IssuesRepository";

    @Inject
    MoviesDataService moviesDataService;
    private ArrayList<ResultsItem> issuesListing;
    private ArrayList<ResultsItem> issueList = new ArrayList<>();
    private MutableLiveData<List<ResultsItem>> mutableLiveData = new MutableLiveData<>();

    public MoviesRepository() {
        MyApplication.getApiComponent().inject(this);
    }

    public MutableLiveData<List<ResultsItem>> getMutableMainLiveData()
    {
        Call<MoviesData> call = moviesDataService.getMoviesList();
        call.enqueue(new Callback<MoviesData>()
        {
            @Override
            public void onResponse(Call<MoviesData> call, Response<MoviesData> response)
            {
                issueList = (ArrayList<ResultsItem>) response.body().getResults();
                if (issueList != null && issueList.size() > 0)
                {
                    mutableLiveData.setValue(issueList);
                }
            }

            @Override
            public void onFailure(Call<MoviesData> call, Throwable t)
            {

            }
        });

        return mutableLiveData;
    }

}


