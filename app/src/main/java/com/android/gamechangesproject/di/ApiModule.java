package com.android.gamechangesproject.di;

import com.android.gamechangesproject.repository.IssuesDataService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private Retrofit retrofit;
    private String BASE_URL = "https://api.github.com/";

    public ApiModule()
    {

    }

    @Singleton
    @Provides
    public IssuesDataService getService()
    {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(IssuesDataService.class);
    }
}
