package com.android.gamechangesproject.repository;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.android.gamechangesproject.di.MyApplication;
import com.android.gamechangesproject.model.IssuesResponse;
import com.android.gamechangesproject.persistent.SharedHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssuesRepository {

    private static final String TAG = "IssuesRepository";
    @Inject
    IssuesDataService issuesDataService;
    private ArrayList<IssuesResponse> issuesListing;
    private ArrayList<IssuesResponse> issueList = new ArrayList<>();
    private MutableLiveData<List<IssuesResponse>> mutableLiveData = new MutableLiveData<>();
    private Application application;
    private Gson gson;

    public IssuesRepository(Application application) {
        this.application = application;
        gson = new Gson();
        MyApplication.getApiComponent().inject(this);
    }

    public MutableLiveData<List<IssuesResponse>> getMutableMainLiveData() {

            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            SharedPreferences myPrefSetting = application.getSharedPreferences("PREF", 0);
            int last_Day = myPrefSetting.getInt("myDay", 0);

            if (last_Day != currentDay)
            {
                SharedPreferences.Editor editor = myPrefSetting.edit();
                editor.putInt("myDay", currentDay);
                editor.apply();
                editor.commit();

                if (issueList != null) {
                    issueList.clear();
                }

                Call<List<IssuesResponse>> call = issuesDataService.getIssuesList();
                call.enqueue(new Callback<List<IssuesResponse>>() {
                    @Override
                    public void onResponse(Call<List<IssuesResponse>> call, Response<List<IssuesResponse>> response) {
                        issueList = (ArrayList<IssuesResponse>) response.body();
                        if (issueList != null && issueList.size() > 0) {
                            String issues_json = gson.toJson(issueList);
                            SharedHelper.putKey(application.getApplicationContext(), "issueData", issues_json);
                        }

                        mutableLiveData.setValue(issueList);
                    }

                    @Override
                    public void onFailure(Call<List<IssuesResponse>> call, Throwable t) {
                    }
                });
            }
            else
            {
                String json = SharedHelper.getKey(application.getApplicationContext(), "issueData");
                Type type = new TypeToken<List<IssuesResponse>>() {
                }.getType();
                issueList = gson.fromJson(json, type);
                if (issueList != null && issueList.size() > 0) {
                    mutableLiveData.setValue(issueList);
                    Log.e(TAG, "Local Persistent Response:" + issueList.size());
                }
            }

        return mutableLiveData;
    }

}


