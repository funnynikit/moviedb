package com.android.insuris.repository;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.android.insuris.di.MyApplication;
import com.android.insuris.model.Response;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailsRepository
{
    private static final String TAG = "CommentRepository";

    @Inject
    MoviesDataService moviesDataService;
    private MutableLiveData<Response> mutableLiveData = new MutableLiveData<>();

    public DetailsRepository(Application application)
    {
        MyApplication.getCommentComponent().inject(this);
    }

    public MutableLiveData<Response> getMutableCommentsLiveData(Integer number)
    {
        Observable<Response> call = moviesDataService.getDetail(number);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>()
                {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response response)
                    {
                        mutableLiveData.setValue(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return mutableLiveData;
    }
}
