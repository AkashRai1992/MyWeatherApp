package com.example.akki.mycustomerweatherapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import Calander.DateView;
import Constants.ConfigConst;
import model.CurrentWeatherData;
import model.ForecastWeatherData;


public class DetailedViewCurrentWeather extends Activity implements View.OnClickListener {

    public LinearLayout detailViewLinearLayout;
    public TextView textview_Current_weather_desc;
    public TextView textview_current_pressure;
    public TextView textview_sunset_time;
    public TextView textview_sunrise_time;
    public TextView textView_ground_level;
    public TextView textView_humidity;
    public TextView textView_sea_level;
    public TextView textView_wind_speed;
    public TextView textView_dew_point;
    public ImageView imageView_current_weather_detail;
    public TextView textview_latitude;
    public TextView textview_longitude;
    public ImageView image_view_forecast_weather01;
    public ImageView image_view_forecast_weather02;
    public ImageView image_view_forecast_weather03;
    public ImageView image_view_forecast_weather04;
    public ImageView image_view_forecast_weather00;
    public TextView text_view_forecast_weather00;
    public TextView text_view_forecast_weather02;
    public TextView text_view_forecast_weather03;
    public TextView text_view_forecast_weather04;
    public TextView text_view_forecast_weather01;
    public TextView text_view_last_updated_time;
    public TextView text_view_forecast_day_name_00;
    public TextView text_view_forecast_day_name_01;
    public TextView text_view_forecast_day_name_02;
    public TextView text_view_forecast_day_name_03;
    public TextView text_view_forecast_day_name_04;
    public Animation animation;
    public Animation animation_right;
    public Animation fade_in_animation;
    public Animation slide_up_animation;

    public static boolean isTouchEnabledForDetailView = false;
    public static CurrentWeatherData result;
    public static ForecastWeatherData forecastWeatherDataResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed__view__current__weather);
        initHolder();
        init();

        // universal image loader
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


    }

    private void initHolder() {

        detailViewLinearLayout = (LinearLayout) findViewById(R.id.detail_view_current_main_layout);
        detailViewLinearLayout.setOnClickListener(this);
        textview_Current_weather_desc = (TextView) findViewById(R.id.textview_Current_weather_desc);
        textview_current_pressure = (TextView) findViewById(R.id.textview_current_pressure);
        textview_sunrise_time = (TextView) findViewById(R.id.textview_sunrise_time);
        textview_sunset_time = (TextView) findViewById(R.id.textview_sunset_time);
        textView_wind_speed = (TextView) findViewById(R.id.textview_wind_speed);
        textView_humidity = (TextView) findViewById(R.id.textview_humidity);
        textView_ground_level = (TextView) findViewById(R.id.textview_grOund_level);
        textView_sea_level = (TextView) findViewById(R.id.textview_sea_level);
        textView_dew_point = (TextView) findViewById(R.id.textview_dew_point);
        imageView_current_weather_detail = (ImageView) findViewById(R.id.current_weather_detail_image_view_icon);
        textview_latitude = (TextView) findViewById(R.id.textview_latitude);
        textview_longitude = (TextView) findViewById(R.id.textview_longitude);
        image_view_forecast_weather00 = (ImageView) findViewById(R.id.image_view_forecast_weather00);
        image_view_forecast_weather01 = (ImageView) findViewById(R.id.image_view_forecast_weather01);
        image_view_forecast_weather02 = (ImageView) findViewById(R.id.image_view_forecast_weather02);
        image_view_forecast_weather03 = (ImageView) findViewById(R.id.image_view_forecast_weather03);
        image_view_forecast_weather04 = (ImageView) findViewById(R.id.image_view_forecast_weather04);
        text_view_forecast_weather00 = (TextView) findViewById(R.id.text_view_forecast_weather00);
        text_view_forecast_weather01 = (TextView) findViewById(R.id.text_view_forecast_weather01);
        text_view_forecast_weather02 = (TextView) findViewById(R.id.text_view_forecast_weather02);
        text_view_forecast_weather03 = (TextView) findViewById(R.id.text_view_forecast_weather03);
        text_view_forecast_weather04 = (TextView) findViewById(R.id.text_view_forecast_weather04);
        text_view_last_updated_time = (TextView) findViewById(R.id.textview_last_updated_date);
        text_view_forecast_day_name_00 = (TextView) findViewById(R.id.text_view_forecast_day_name_00);
        text_view_forecast_day_name_01 = (TextView) findViewById(R.id.text_view_forecast_day_name_01);
        text_view_forecast_day_name_02 = (TextView) findViewById(R.id.text_view_forecast_day_name_02);
        text_view_forecast_day_name_03 = (TextView) findViewById(R.id.text_view_forecast_day_name_03);
        text_view_forecast_day_name_04 = (TextView) findViewById(R.id.text_view_forecast_day_name_04);

    }

    public void init() {
        if (result != null && forecastWeatherDataResult != null) {
            textview_Current_weather_desc.setText((CharSequence) result.weather_var.get(0).weather_desc);
            textview_current_pressure.setText("Pressure is " + String.valueOf((int) result.temperature.pressure + "pa"));
            textview_sunrise_time.setText("Sunrise time is " + DateView.getDateCurrentTimeZone(result.sunriseSunset.Sunrise));
            textview_sunset_time.setText("Sunset time is " + DateView.getDateCurrentTimeZone(result.sunriseSunset.Sunset));
            textView_wind_speed.setText("Wind speed is " + String.valueOf(result.windAtr.windSpeed + "km/hr"));
            textView_humidity.setText("humidity is " + String.valueOf(result.temperature.humidity));
            textView_ground_level.setText(" Ground level is" + String.valueOf(result.temperature.groundLevel));
            textView_sea_level.setText("Sea level is" + String.valueOf(result.temperature.sea_level));
            textView_dew_point.setText("Dew point is " + String.valueOf(result.windAtr.dewpoint) + (char) 0x00B0 + " F");
            textview_latitude.setText("latitude is " + String.valueOf(result.cordinates.latitude));
            textview_longitude.setText("longitude is " + String.valueOf(result.cordinates.longitude));

            text_view_forecast_weather00.setText(String.valueOf(forecastWeatherDataResult.temp_var.get(0).forecastTempDetails.avg_temp) + (char) 0x00B0 + "C");
            text_view_forecast_weather01.setText(String.valueOf(forecastWeatherDataResult.temp_var.get(1).forecastTempDetails.avg_temp) + (char) 0x00B0 + "C");
            text_view_forecast_weather02.setText(String.valueOf(forecastWeatherDataResult.temp_var.get(2).forecastTempDetails.avg_temp) + (char) 0x00B0 + "C");
            text_view_forecast_weather03.setText(String.valueOf(forecastWeatherDataResult.temp_var.get(3).forecastTempDetails.avg_temp) + (char) 0x00B0 + "C");
            text_view_forecast_weather04.setText(String.valueOf(forecastWeatherDataResult.temp_var.get(4).forecastTempDetails.avg_temp) + (char) 0x00B0 + "C");

            text_view_forecast_day_name_00.setText(DateView.getDayName(1));
            text_view_forecast_day_name_01.setText(DateView.getDayName(2));
            text_view_forecast_day_name_02.setText(DateView.getDayName(3));
            text_view_forecast_day_name_03.setText(DateView.getDayName(4));
            text_view_forecast_day_name_04.setText(DateView.getDayName(5));

            setWeatherIcon(imageView_current_weather_detail, 0);

            fade_in_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_animation);
            animation_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_animation_right);
            slide_up_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Log.d("Time", " left animation started" + String.valueOf(System.currentTimeMillis()));
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.d("Time", " left animation end" + String.valueOf(System.currentTimeMillis()));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            fade_in_animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Log.d("Time", "fade in animation started" + String.valueOf(System.currentTimeMillis()));
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.d("Time", "fade in animation end" + String.valueOf(System.currentTimeMillis()));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            animation_right.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Log.d("Time", "right animation started" + String.valueOf(System.currentTimeMillis()));
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.d("Time", "right animation end" + String.valueOf(System.currentTimeMillis()));

                    text_view_last_updated_time.setText("last updated time  " + DateView.getDateCurrentTimeZone(result.last_updated_time));
                    text_view_last_updated_time.startAnimation(fade_in_animation);

                    setWeatherIconAsPerIndex(image_view_forecast_weather00, 0);
                    setWeatherIconAsPerIndex(image_view_forecast_weather01, 1);
                    setWeatherIconAsPerIndex(image_view_forecast_weather02, 2);
                    setWeatherIconAsPerIndex(image_view_forecast_weather03, 3);
                    setWeatherIconAsPerIndex(image_view_forecast_weather04, 4);


                    image_view_forecast_weather00.startAnimation(slide_up_animation);
                    image_view_forecast_weather01.startAnimation(slide_up_animation);
                    image_view_forecast_weather02.startAnimation(slide_up_animation);
                    image_view_forecast_weather03.startAnimation(slide_up_animation);
                    image_view_forecast_weather04.startAnimation(slide_up_animation);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }


            });

//            textView_wind_speed.postDelayed(new Runnable() {
//                @Override
//                public void run() {
            textView_wind_speed.startAnimation(animation);
            textView_sea_level.startAnimation(animation);
            textView_ground_level.startAnimation(animation);
            textView_humidity.startAnimation(animation);
            textView_dew_point.startAnimation(animation_right);
            textview_longitude.startAnimation(animation_right);
            textview_latitude.startAnimation(animation_right);
//                }
//            }, 300);
        }
    }


    private void setWeatherIconAsPerIndex(ImageView imageView, int index) {

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
        url = ConfigConst.URL_CURRENT_WEATHER_ICON + forecastWeatherDataResult.temp_var.get(index).weatherVariables_forecast.get(0).weather_icon_id + ".png";
        Log.d("setWeather", url);
        //Log.d("Weather", url);
        //    url="http://openweathermap.org/img/w/10d.png";

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).resetViewBeforeLoading(true).showImageForEmptyUri(R.drawable.abc_item_background_holo_dark).showImageOnFail(R.drawable.abc_item_background_holo_dark).showImageOnLoading(R.drawable.abc_item_background_holo_dark).build();


        imageLoader.displayImage(url, imageView, options);
        // image_view_forecast_weather00.startAnimation(slide_up_animation);


    }

    private void setWeatherIcon(ImageView imageView, int index) {

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
        url = ConfigConst.URL_CURRENT_WEATHER_ICON + result.weather_var.get(index).weather_icon_id + ".png";

        Log.d("Weather", url);
        //    url="http://openweathermap.org/img/w/10d.png";

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).resetViewBeforeLoading(true).showImageForEmptyUri(R.drawable.abc_item_background_holo_dark).showImageOnFail(R.drawable.abc_item_background_holo_dark).showImageOnLoading(R.drawable.abc_item_background_holo_dark).build();


        imageLoader.displayImage(url, imageView, options);


    }

    private void startThreeHourFormatActivity() {
        Intent intent = new Intent(this, ThreeHourFormatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_view_current_main_layout:
                if (isTouchEnabledForDetailView)
                    startThreeHourFormatActivity();
                break;
            default:
                break;

        }


    }
}
