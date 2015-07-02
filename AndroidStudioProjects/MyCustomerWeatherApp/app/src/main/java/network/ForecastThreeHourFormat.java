package network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.akki.mycustomerweatherapp.DetailedViewCurrentWeather;
import com.example.akki.mycustomerweatherapp.MainActivity;
import com.example.akki.mycustomerweatherapp.ThreeHourFormatActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import Constants.NetworkConst;
import model.CurrentWeatherData;
import model.FutureWeatherForecastThreeHourFormatModelData;

public class ForecastThreeHourFormat extends AsyncTask<String, Void, FutureWeatherForecastThreeHourFormatModelData> {
    private MainActivity mainActivity;
    private DetailedViewCurrentWeather detailed_view_current_weather;
    private String url;

    public ForecastThreeHourFormat(MainActivity mainActivity, String url) {
        this.mainActivity = mainActivity;
        this.url = url;
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    public FutureWeatherForecastThreeHourFormatModelData doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        URL url = null;
        FutureWeatherForecastThreeHourFormatModelData object = null;

        try {
            url = new URL(this.url + "?" + params[0]);
            Log.e(NetworkConst.TAG_REST + getClass().getSimpleName(), "doInBackground() URLof forecastthreehour format :> " + url);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod(NetworkConst.REQ_METHOD_GET);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            urlConnection.connect();

            InputStream inStream = null;
            inStream = urlConnection.getInputStream();
            Reader reader = new InputStreamReader(inStream);

            Gson gson = new GsonBuilder().create();
            object = gson.fromJson(reader, FutureWeatherForecastThreeHourFormatModelData.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (object);
    }


    @Override
    public void onPostExecute(FutureWeatherForecastThreeHourFormatModelData result) {
        super.onPostExecute(result);

        Log.e(NetworkConst.TAG_REST + getClass().getSimpleName(), "doInBackground() RESPONSE :> " +result);

        if (result != null) {

            Log.e(NetworkConst.TAG_REST + getClass().getSimpleName(), "response is not null yettttttttt " );

            ThreeHourFormatActivity.futureWeatherForecastThreeHourFormatModelData = result;

            DetailedViewCurrentWeather.isTouchEnabledForDetailView=true;

        }
    }


}