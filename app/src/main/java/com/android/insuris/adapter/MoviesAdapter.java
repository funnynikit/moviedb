package com.android.insuris.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.insuris.R;
import com.android.insuris.databinding.MovieListItemBinding;
import com.android.insuris.model.RatingData;
import com.android.insuris.model.ResultsItem;
import com.android.insuris.repository.RatingRepository;
import com.android.insuris.view.DetailActivity;
import com.android.insuris.view.MainActivity;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> implements ItemEventListener {

    private static final String TAG = "MoviesAdapter";
    private List<ResultsItem> resultsItem;
    private Context context;

    public MoviesAdapter(List<ResultsItem> issuesRespons, MainActivity context) {
       this.resultsItem = issuesRespons;
       this.context=context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MovieListItemBinding movieResponseListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.movie_list_item, viewGroup, false);
        return new MovieViewHolder(movieResponseListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int i)
    {
        final ResultsItem movieItem = resultsItem.get(i);
        movieViewHolder.movieResponseListItemBinding.setResultResponse(movieItem);
        movieViewHolder.movieResponseListItemBinding.setItemClickListener(this);
        movieViewHolder.movieResponseListItemBinding.submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                float rating = movieViewHolder.movieResponseListItemBinding.simpleRatingBar.getRating();
                RatingData ratingData=new RatingData();
                ratingData.setValue(rating*2);
                RatingRepository ratingRepository=new RatingRepository(ratingData,movieItem.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (resultsItem != null) {
            return resultsItem.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onClickItem(ResultsItem i) {
        Log.e(TAG,"On Click Item:"+i);
        Intent intent=new Intent(context, DetailActivity.class);
        intent.putExtra("id",i.getId());
        context.startActivity(intent);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieListItemBinding movieResponseListItemBinding;

        public MovieViewHolder(@NonNull MovieListItemBinding movieResponseListItemBinding) {
            super(movieResponseListItemBinding.getRoot());
            this.movieResponseListItemBinding = movieResponseListItemBinding;
        }
    }
}