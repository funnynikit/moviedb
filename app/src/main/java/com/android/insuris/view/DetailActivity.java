package com.android.insuris.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.android.insuris.R;
import com.android.insuris.adapter.DetailsAdapter;
import com.android.insuris.databinding.ActivityDetailBinding;
import com.android.insuris.model.Response;
import com.android.insuris.viewmodel.DetailsViewModel;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG="DetailActivity";
    private ActivityDetailBinding activityDetailBinding;
    private DetailsViewModel detailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        int id=(int) getIntent().getIntExtra("id",0);
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        getDetails(id);
    }

    private void getDetails(Integer number)
    {
        detailsViewModel.getDetails(number).observe(this, new Observer<Response>()
        {
            @Override
            public void onChanged(Response response)
            {
                DetailsAdapter adapter = new DetailsAdapter(response.getGenres());
                activityDetailBinding.setDetailsAdapter(adapter);
            }
        });
    }
}
