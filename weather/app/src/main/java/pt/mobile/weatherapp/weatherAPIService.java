package pt.mobile.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherAPIService {
    @GET("forecast")
    Call<weatherRespon> getWeatherForecast(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("daily") String daily,
            @Query("current_weather") boolean cWeather,
            @Query("timezone") String time);
}
