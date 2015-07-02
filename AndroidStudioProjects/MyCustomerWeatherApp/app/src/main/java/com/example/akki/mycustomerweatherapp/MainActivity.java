package com.example.akki.mycustomerweatherapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.DialogPreference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import Calander.DateView;
import Constants.KeyConst;
import model.CurrentWeatherData;
import model.ForecastWeatherData;
import network.CallApi;
import Constants.ConfigConst;
import network.ForecastThreeHourFormat;
import network.FutureWeatherForecastApi;


public class MainActivity extends Activity implements LocationListener, View.OnClickListener {

    protected LocationManager locationManager;
    Location location = null;
    public double latitude;
    public double longitude;

    public boolean alert_Dialog_button_pressed = false;

    public LinearLayout linearLayout;
    public ProgressBar progressBar;
    public Button StartFutureActivity;

    public LinearLayout linearLayout_landscape;
    public ProgressBar progressBar_landscape;
    public Button StartFutureActivity_landscape;

    public TextView textView_country_name_landscape;
    public TextView textView_avg_temp_landscape;
    public TextView textView_min_temp_landscape;
    public TextView textView_max_temp_landscape;
    public TextView textView_loading_text_landscape;
    public ImageView imageView_icon_landscape;
    public View view_divider_lanscape;

    public static String cityName;
    public static String cityNameFromLocation;
    public String stateName;
    public String countryName;
    public Context mContext;

    public TextView textView_country_name;
    public TextView textView_avg_temp;
    public TextView textView_min_temp;
    public TextView textView_max_temp;
    public TextView text_view_current_weather_description;
    public TextView textView_loading_text;
    protected TextView textView_day_name;
    public ImageView imageView_icon;
    public View view_divider;


    public int lat;
    public int liong;

    public int BackgroundColour;
    public boolean IsLandscapeModeEnabled = false;
    public Animation anim_fadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGpsService();


        mContext = this;
        locationManager = (LocationManager) mContext
                .getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
        location = locationManager
                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (location != null) {
            lat = (int) location.getLatitude();
            liong = (int) location.getLongitude();
            Toast.makeText(this, "Your Location is - \nLat: " + lat + "\nLong: " + liong, Toast.LENGTH_LONG).show();

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(lat, liong, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            cityNameFromLocation = addresses.get(0).getAddressLine(0);
            countryName = addresses.get(0).getAddressLine(1);


            Log.d("Cord", cityNameFromLocation + stateName + countryName + String.valueOf(lat) + String.valueOf(liong) + addresses.toString());

        }


        initViewHolder();


        // universal image loader


        BackgroundColour = getResources().getColor(R.color.Bg_color);

        Log.d("cord", "latitude is" + String.valueOf(lat) + "longitude is" + String.valueOf(liong));
        new CallApi(this, ConfigConst.URL_CURRENT_WEATHER).execute("lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(liong) + "&cnt=10&mode=json&units=metric");
        //lat=35&lon=139&cnt=10&mode=json&units=metric
        //q=London,uk&units=metric
    }


    private void setUIonCreate() {
        textView_loading_text.setText("Loading...");
        progressBar.setVisibility(View.VISIBLE);
        imageView_icon.setVisibility(View.GONE);
        view_divider.setVisibility(View.GONE);
        linearLayout.setOnClickListener(this);


    }

    private void setUIonCreateLandscape() {
        textView_loading_text_landscape.setText("Loading...");
        progressBar_landscape.setVisibility(View.VISIBLE);
        imageView_icon_landscape.setVisibility(View.GONE);
        view_divider_lanscape.setVisibility(View.GONE);
        linearLayout_landscape.setOnClickListener(this);


    }


    private void initViewHolderLandscape() {


    }


    private void initViewHolder() {
        linearLayout = (LinearLayout) findViewById(R.id.main_Layout);
        linearLayout.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        textView_country_name = (TextView) findViewById(R.id.textview_country_name);
        textView_avg_temp = (TextView) findViewById(R.id.textview_average_temp);
        textView_min_temp = (TextView) findViewById(R.id.textview_min_temp);
        textView_max_temp = (TextView) findViewById(R.id.textview_max_temp);
        textView_loading_text = (TextView) findViewById(R.id.textview_Loding_text);
        view_divider = (View) findViewById(R.id.view_divider);
        imageView_icon = (ImageView) findViewById(R.id.image_view_icon);
        textView_day_name = (TextView) findViewById(R.id.textview_day_of_the_week);
        text_view_current_weather_description = (TextView) findViewById(R.id.text_view_current_weather_description);


    }

    public void initLanscape(CurrentWeatherData result) {
        if (IsLandscapeModeEnabled) {

            setUIonCreateLandscape();
            initViewHolderLandscape();

            textView_country_name_landscape.setText(result.CoutryName);


            textView_max_temp_landscape.setText(String.valueOf(result.temperature.temp_max));
            textView_min_temp_landscape.setText(String.valueOf(result.temperature.temp_min));
            textView_avg_temp_landscape.setText(String.valueOf((int) result.temperature.temp_avg));
            textView_loading_text_landscape.setVisibility(View.GONE);
            StartFutureActivity_landscape.setOnClickListener(this);

            linearLayout_landscape.setBackgroundColor(BackgroundColour);
            progressBar_landscape.setVisibility(View.INVISIBLE);
            imageView_icon_landscape.setVisibility(View.VISIBLE);
            view_divider_lanscape.setVisibility(View.VISIBLE);


            new FutureWeatherForecastApi(this, ConfigConst.URL_FUTURE_WEATHER).execute("lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(liong), "&cnt=10&mode=json&units=metric");
            //lat=35&lon=139&cnt=10&mode=json&units=metric
        }
    }


    public void init(CurrentWeatherData result) {
        textView_country_name.setText(cityNameFromLocation + "," + countryName);

        String.valueOf((int) result.temperature.temp_max);
        textView_max_temp.setText(String.valueOf(result.temperature.temp_max) + (char) 0x00B0 + "C");
        textView_min_temp.setText(String.valueOf(result.temperature.temp_min) + (char) 0x00B0 + "C");
        textView_avg_temp.setText(String.valueOf((int) result.temperature.temp_avg) + (char) 0x00B0 + "C");
        textView_loading_text.setVisibility(View.GONE);
        textView_day_name.setText(DateView.getDayName(0));
        text_view_current_weather_description.setText((CharSequence) result.weather_var.get(0).weather_desc);


        linearLayout.setBackgroundColor(BackgroundColour);
        progressBar.setVisibility(View.INVISIBLE);
        imageView_icon.setVisibility(View.VISIBLE);
        view_divider.setVisibility(View.VISIBLE);
        anim_fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        //start the animation on weather icon
        imageView_icon.startAnimation(anim_fadein);

        new FutureWeatherForecastApi(this, ConfigConst.URL_FUTURE_WEATHER).execute("lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(liong) + "&mode=json&units=metric");
        new ForecastThreeHourFormat(this, ConfigConst.URL_FUTURE_WEATHER_FORMAT).execute("lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(liong) + "&mode=json&units=metric");
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.main_Layout:
                if (FutureWeatherForecastApi.touchEnabled) {

                    launchDetailedActivity();
                }
                break;


            default:
                break;
        }


    }

    public void doAction(ForecastWeatherData result) {
        Intent intent = new Intent(this, FutureForCastActivity.class);
        intent.putExtra(KeyConst.KEY_DATA, result);
        startActivity(intent);
    }


    public void setWeatherIcon(CurrentWeatherData result) {

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


        url = ConfigConst.URL_CURRENT_WEATHER_ICON + result.weather_var.get(0).weather_icon_id + ".png";

        Log.d("Weather", url);
        //    url="http://openweathermap.org/img/w/10d.png";

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).resetViewBeforeLoading(true).showImageForEmptyUri(R.drawable.abc_item_background_holo_dark).showImageOnFail(R.drawable.abc_item_background_holo_dark).showImageOnLoading(R.drawable.abc_item_background_holo_dark).build();


        imageLoader.displayImage(url, imageView_icon, options);


    }

    private void launchDetailedActivity() {
        Log.d("launchDetail", "inside launchDetailedActivit");
        Intent intent = new Intent(this, DetailedViewCurrentWeather.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("mode", "onconfig called");
            IsLandscapeModeEnabled = true;


        }

    }

    private void startGpsService() {
        Intent serviceIntent = new Intent(this, GPSService.class);
        startService(serviceIntent);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(this, GPSService.class);

        stopService(intent);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mContext,"Press back button again to exit",Toast.LENGTH_LONG);

               MainActivity.super.onBackPressed();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle("Are you sure you want to exit? ");
        alertDialog.show();
    }


}






/*
        mContext = this;
        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        if (locationManager == null) {
            Toast.makeText(this, "null poniter exception", Toast.LENGTH_LONG);

        } else {
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled == true) {


                location = locationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                // latitude = location.getLatitude();
                //longitude = location.getLongitude();
                Toast.makeText(this, "Latitude is" + latitude + "and longitude is" + longitude, Toast.LENGTH_LONG);
            } else {
                Toast.makeText(this, "gps not enabled", Toast.LENGTH_LONG);

            }
        }
        */

// startService(new Intent(this, MyService.class));