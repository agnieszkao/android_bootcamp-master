package com.droidonroids.weatherbootcamp.data.network;

import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiService {

    @GET("/forecast")
    ForecastResponse getForecastWithAsync(@Query("q") String cityName);

    @GET("/forecast") void getWeatherWithCallback(@Query("q") String cityName, Callback<ForecastResponse> callback);

}
