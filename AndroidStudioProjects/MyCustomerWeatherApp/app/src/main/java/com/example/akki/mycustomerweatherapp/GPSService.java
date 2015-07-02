package com.example.akki.mycustomerweatherapp;

import android.app.Service;
import android.content.Context;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class GPSService extends Service implements LocationListener {

    public Context mContext;
    public LocationManager locationManager;

     public static int lat;
     public static int liong;
    Location location;

    public GPSService() {
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mContext = this;
        Log.d("cord","Inside service");
        locationManager = (LocationManager) mContext
                .getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
        location = locationManager
                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (location != null) {
             lat = (int)location.getLatitude();
             liong =(int)location.getLongitude();
            Toast.makeText(this, "Your Location is - \nLat: " + lat + "\nLong: " + liong, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("cord"," destroy  service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
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
}
