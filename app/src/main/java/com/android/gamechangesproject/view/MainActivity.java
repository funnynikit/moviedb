package com.android.gamechangesproject.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;

import com.android.gamechangesproject.R;
import com.android.gamechangesproject.adapter.IssuesAdapter;
import com.android.gamechangesproject.databinding.ActivityMainBinding;
import com.android.gamechangesproject.model.IssuesResponse;
import com.android.gamechangesproject.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private final static String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getAllIssues();
    }

    private void getAllIssues() {

        mainViewModel.getAllIssues().observe(this, new Observer<List<IssuesResponse>>() {

            @Override
            public void onChanged(List<IssuesResponse> issuesResponses) {
                    Log.e(TAG,"Issue Response:"+issuesResponses.size());
                    IssuesAdapter adapter = new IssuesAdapter(issuesResponses, MainActivity.this);
                    binding.setIssuesAdapter(adapter);
            }
        });
    }
}
