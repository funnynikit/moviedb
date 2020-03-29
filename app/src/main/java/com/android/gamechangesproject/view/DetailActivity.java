package com.android.gamechangesproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import com.android.gamechangesproject.R;
import com.android.gamechangesproject.adapter.CommentsAdapter;
import com.android.gamechangesproject.adapter.IssuesAdapter;
import com.android.gamechangesproject.databinding.ActivityDetailBinding;
import com.android.gamechangesproject.model.CommentResponse;
import com.android.gamechangesproject.model.IssuesResponse;
import com.android.gamechangesproject.viewmodel.CommentViewModel;
import com.android.gamechangesproject.viewmodel.MainViewModel;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG="DetailActivity";
    private ActivityDetailBinding activityDetailBinding;
    private CommentViewModel commentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        int number=(int) getIntent().getIntExtra("number",0);
        Log.e(TAG,"ISSUES :"+number);
        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        getComments(number);
    }

    private void getComments(Integer number) {

        commentViewModel.getAllComments(number).observe(this, new Observer<List<CommentResponse>>() {

            @Override
            public void onChanged(List<CommentResponse> commentsResponses) {

                Log.e(TAG,"ISSUES LIST SIZE:"+commentsResponses.size());

                CommentsAdapter adapter = new CommentsAdapter(commentsResponses);
                activityDetailBinding.setCommentsAdapter(adapter);
            }
        });
    }
}
