package com.android.gamechangesproject.repository;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.android.gamechangesproject.di.MyApplication;
import com.android.gamechangesproject.model.CommentResponse;
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

public class CommentRepository {

    private static final String TAG = "CommentRepository";
    private Application application;
    private Gson gson;

    @Inject
    IssuesDataService issuesDataService;

    private ArrayList<CommentResponse> commentList = new ArrayList<>();
    private MutableLiveData<List<CommentResponse>> mutableLiveData = new MutableLiveData<>();

    public CommentRepository(Application application) {
        this.application=application;
        MyApplication.getCommentComponent().inject(this);
        gson = new Gson();
    }

    public MutableLiveData<List<CommentResponse>> getMutableCommentsLiveData(Integer number) {

            Calendar calendar=Calendar.getInstance();
            int currentDay=calendar.get(Calendar.DAY_OF_MONTH);
            SharedPreferences settings=application.getSharedPreferences("PREFS",0);
            int lastDay=settings.getInt("day",0);
            if(lastDay!=currentDay)
            {
                SharedPreferences.Editor editor=settings.edit();
                editor.putInt("day",currentDay);
                editor.apply();

                if(commentList!=null)
                {
                    commentList.clear();
                }

                Call<List<CommentResponse>> call = issuesDataService.getCommentsList(number);
                call.enqueue(new Callback<List<CommentResponse>>() {

                    @Override
                    public void onResponse(Call<List<CommentResponse>> call, Response<List<CommentResponse>> response) {
                        Log.e(TAG, "Comment Response:" + response);
                        commentList = (ArrayList<CommentResponse>) response.body();
                        // put data into local
                        if(commentList!=null && commentList.size()>0)
                        {
                            String comment_json = gson.toJson(commentList);
                            SharedHelper.putKey(application.getApplicationContext(),"commentData",comment_json);
                        }
                        //
                        mutableLiveData.setValue(commentList);
                    }

                    @Override
                    public void onFailure(Call<List<CommentResponse>> call, Throwable t) {
                        Log.e(TAG, "Comment Response Error:" + call + "T:" + t);
                    }
                });
            }
            else
            {
                String json = SharedHelper.getKey(application.getApplicationContext(),"commentData");
                Type type = new TypeToken<List<CommentResponse>>() {}.getType();
                commentList = gson.fromJson(json, type);
                if(commentList!=null && commentList.size()>0)
                {
                    mutableLiveData.setValue(commentList);
                    Log.e(TAG, "Local Persistent Response:" + commentList.size());
                }
            }

        // else call from remote
        return mutableLiveData;
    }

}
