package com.android.insuris.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.android.insuris.R;
import com.android.insuris.adapter.MoviesAdapter;
import com.android.insuris.databinding.ActivityMainBinding;
import com.android.insuris.model.ResultsItem;
import com.android.insuris.viewmodel.MainViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private final static String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getMovies();
    }

    private void getMovies()
    {
        mainViewModel.getAllIssues().observe(this, new Observer<List<ResultsItem>>()
        {
            @Override
            public void onChanged(List<ResultsItem> resultsItemList) {
                    MoviesAdapter adapter = new MoviesAdapter(resultsItemList, MainActivity.this);
                    binding.setMoviesAdapter(adapter);
            }
        });
    }
}
