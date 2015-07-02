package com.example.akki.mycustomerweatherapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Constants.KeyConst;
import model.ForecastWeatherData;


public class FutureForCastActivity extends Activity {
    public static ListView listView;
    public TextView future_textview_humidity;
    public TextView future_textview_pressure;
    public static ForecastWeatherData result;
    public Object obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_for_cast);

        result = (ForecastWeatherData) getIntent().getSerializableExtra(KeyConst.KEY_DATA);
        initViewHolder();
        init();
    }


    private void initViewHolder() {
        listView = (ListView) findViewById(R.id.List_View);
       // future_textview_humidity = (TextView) findViewById(R.id.textview_humidity_forecast);
       // future_textview_pressure = (TextView) findViewById(R.id.textview_pressure_forecast);
    }


    private void init() {
        ArrayList<String> array = new ArrayList<>();


        for (int i = 0; i < result.temp_var.size(); i++) {
            array.add(String.valueOf(result.temp_var.get(i).humidity));
        }


      //  ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_view_layout, R.id.textview_humidity_forecast, array);

     //   listView.setAdapter(adapter);
    }


}







