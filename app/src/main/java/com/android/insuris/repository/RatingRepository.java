package com.android.insuris.repository;

import com.android.insuris.di.MyApplication;
import com.android.insuris.model.RatingData;
import com.android.insuris.model.RatingResponse;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RatingRepository {

    private RatingData ratData;
    private int movie_id;
    private Observable<RatingResponse> call;

    @Inject
    MoviesDataService moviesDataService;

    public RatingRepository(RatingData ratingData, int id)
    {
        MyApplication.getCommentComponent().inject(this);
        ratData=ratingData;
        movie_id=id;
        setRating();
    }

    private void setRating()
    {
        call = moviesDataService.setRating(movie_id,ratData);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RatingResponse>()
                {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RatingResponse response) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}
