package com.android.insuris.di;

import com.android.insuris.repository.MoviesDataService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private Retrofit retrofit;
    private String BASE_URL = "https://api.themoviedb.org/";

    public ApiModule()
    {

    }

    @Singleton
    @Provides
    public MoviesDataService getService()
    {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(MoviesDataService.class);
    }
}
