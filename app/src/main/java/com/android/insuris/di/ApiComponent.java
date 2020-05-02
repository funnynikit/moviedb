package com.android.insuris.di;

import com.android.insuris.repository.MoviesRepository;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(MoviesRepository moviesRepository);
}
