package com.example.HW_19_Earthquake;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by абишевас on 24.04.2018.
 */

public class EarthquakeApiImpl implements EarthquakeApi {

    @Override
    public Observable<EarthquakesBase> getEarthquakes() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
                .build();

        EarthquakeApi api = retrofit.create(EarthquakeApi.class);

        return api.getEarthquakes();
    }
}
