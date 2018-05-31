package com.example.HW_19_Earthquake;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by абишевас on 24.04.2018.
 */

public interface EarthquakeApi {
    @GET("query?format=geojson&starttime=2014-01-01&endtime=2014-01-02")
    Observable<EarthquakesBase> getEarthquakes();
}
