package com.example.akki.mycustomerweatherapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.Iterator;

import Adapter.CustomAdapter;
import Constants.ConfigConst;
import model.CurrentWeatherData;
import model.FutureWeatherForecastThreeHourFormatDetails;
import model.FutureWeatherForecastThreeHourFormatModelData;


public class ThreeHourFormatActivity extends Activity {
    public static FutureWeatherForecastThreeHourFormatModelData futureWeatherForecastThreeHourFormatModelData;
    public static ArrayList<FutureWeatherForecastThreeHourFormatDetails> adapterArrayList;
    int index = 0;
    public static Context context;
    public TextView  textView_city_name;

    public  static ThreeHourFormatActivity threeHourFormatActivtiy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_hour_format);
        textView_city_name=(TextView)findViewById(R.id.city_name_text_view);
        textView_city_name.setText(MainActivity.cityNameFromLocation);

        context = this;
        threeHourFormatActivtiy=this;


      adapterArrayList = new ArrayList<>();

        Iterator iterator = futureWeatherForecastThreeHourFormatModelData.futureWeatherForecastThreeHourFormatDetails.iterator();

        // problem here

        while (index<futureWeatherForecastThreeHourFormatModelData.futureWeatherForecastThreeHourFormatDetails.size()-1) {
            adapterArrayList.add(futureWeatherForecastThreeHourFormatModelData.futureWeatherForecastThreeHourFormatDetails.get(index++));

        }

        CustomAdapter customAdapter = new CustomAdapter();
        ListView listView = (ListView) findViewById(R.id.list_view_custom_adapter);
        listView.setAdapter(customAdapter);
    }

    public static int getCount() {

        return adapterArrayList.size();
    }


    public  void setWeatherIcon(int index, ImageView imageView) {


        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);

        String url = null;


        url = ConfigConst.URL_CURRENT_WEATHER_ICON + futureWeatherForecastThreeHourFormatModelData.futureWeatherForecastThreeHourFormatDetails.get(index).weather_var.get(0).weather_icon_id + ".png";

        Log.d("image", url);
        //    url="http://openweathermap.org/img/w/10d.png";

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).resetViewBeforeLoading(true).showImageForEmptyUri(R.drawable.abc_item_background_holo_dark).showImageOnFail(R.drawable.abc_item_background_holo_dark).showImageOnLoading(R.drawable.abc_item_background_holo_dark).build();
          Log.d("image",imageView.toString());

        imageLoader.displayImage(url, imageView, options);


    }


}



