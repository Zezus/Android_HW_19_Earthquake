package com.example.HW_19_Earthquake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private List<Feature> features;
    private EarthquakeAdapter earthquakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        features = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler_view_activity_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        earthquakeAdapter = new EarthquakeAdapter(getApplicationContext(), features);
        recyclerView.setAdapter(earthquakeAdapter);

        EarthquakeApi api = new EarthquakeApiImpl();
        api.getEarthquakes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        earthquakesResult -> {
                            int counter = 0;
                            for (Feature feature : earthquakesResult.getFeatures()) {
                                features.add(feature);
                                counter++;
                                if (counter == 10) {
                                    break;
                                }
                            }
                            earthquakeAdapter.notifyDataSetChanged();
                        },
                        throwable -> {
                            // обработка ошибки
                        }
                );
    }
}
