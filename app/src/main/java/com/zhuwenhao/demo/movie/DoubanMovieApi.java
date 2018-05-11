package com.zhuwenhao.demo.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DoubanMovieApi {

    String apiKey = "?apikey=0b2bdeda43b5688921839c8ecb20399b";

    @GET("in_theaters" + apiKey)
    Call<Movie> getInTheaters(@Query("city") String city, @Query("start") int start, @Query("count") int count);
}