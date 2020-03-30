package com.android.gamechangesproject.repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.android.gamechangesproject.di.MyApplication;
import com.android.gamechangesproject.model.CommentResponse;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {

    private static final String TAG = "CommentRepository";

    @Inject
    IssuesDataService issuesDataService;

    private ArrayList<CommentResponse> commentList = new ArrayList<>();
    private MutableLiveData<List<CommentResponse>> mutableLiveData = new MutableLiveData<>();

    public CommentRepository(Application application) {
        MyApplication.getCommentComponent().inject(this);

    }

    public MutableLiveData<List<CommentResponse>> getMutableCommentsLiveData(Integer number) {

        Call<List<CommentResponse>> call = issuesDataService.getCommentsList(number);
        call.enqueue(new Callback<List<CommentResponse>>() {

            @Override
            public void onResponse(Call<List<CommentResponse>> call, Response<List<CommentResponse>> response) {
                Log.e(TAG, "Comment Response:" + response);
                commentList = (ArrayList<CommentResponse>) response.body();
                mutableLiveData.setValue(commentList);
            }

            @Override
            public void onFailure(Call<List<CommentResponse>> call, Throwable t) {
                Log.e(TAG, "Comment Response Error:" + call + "T:" + t);
            }
        });

        return mutableLiveData;
    }

}
