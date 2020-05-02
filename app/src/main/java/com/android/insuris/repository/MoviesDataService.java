package com.android.insuris.repository;

import com.android.insuris.model.MoviesData;
import com.android.insuris.model.RatingData;
import com.android.insuris.model.RatingResponse;
import com.android.insuris.model.Response;
import com.android.insuris.utils.Constants;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MoviesDataService {

    @GET("3/movie/popular?api_key="+ Constants.api_key +"&language=en-US&page=1")
    Call<MoviesData> getMoviesList();

    @GET("3/movie/{id}?api_key="+ Constants.api_key +"&language=en-US")
    Observable<Response> getDetail(@Path(value = "id", encoded = true) int id);

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("3/movie/{movie_id}/rating?api_key="+Constants.api_key)
    Observable<RatingResponse> setRating(@Path(value = "movie_id", encoded = true) int movie_id, @Body RatingData ratingData);
}
