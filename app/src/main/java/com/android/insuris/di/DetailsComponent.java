package com.android.insuris.di;

import com.android.insuris.repository.DetailsRepository;
import com.android.insuris.repository.RatingRepository;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface DetailsComponent {

    void inject(DetailsRepository detailsRepository);
    void inject(RatingRepository ratingRepository);
}
