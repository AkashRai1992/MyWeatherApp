package network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.akki.mycustomerweatherapp.DetailedViewCurrentWeather;
import com.example.akki.mycustomerweatherapp.FutureForCastActivity;
import com.example.akki.mycustomerweatherapp.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import Constants.NetworkConst;
import model.ForecastWeatherData;

public class FutureWeatherForecastApi extends AsyncTask<String, Void, ForecastWeatherData> {

    private MainActivity mainActivity;
    private FutureForCastActivity futureForCastActivity;
    private String url;
     public static boolean   touchEnabled=false;
    public FutureWeatherForecastApi(MainActivity mainActivity, String url) {
        this.mainActivity = mainActivity;

        this.url = url;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected ForecastWeatherData doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        URL url = null;
        ForecastWeatherData object = null;

        try {
            url = new URL(this.url + "?" + params[0]);
            Log.e(NetworkConst.TAG_REST + getClass().getSimpleName(), "doInBackground() URL :> " + url);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod(NetworkConst.REQ_METHOD_GET);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            urlConnection.connect();

            InputStream inStream = null;
            inStream = urlConnection.getInputStream();
            Reader reader = new InputStreamReader(inStream);

            Gson gson = new GsonBuilder().create();
            object = gson.fromJson(reader, ForecastWeatherData.class);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return (object);
    }


    @Override
    protected void onPostExecute(ForecastWeatherData result) {
        super.onPostExecute(result);
        Log.e(NetworkConst.TAG_REST + getClass().getSimpleName(), "onPostExecute() RESPONSE :> " + result);
//        FutureForCastActivity.result=result;
        DetailedViewCurrentWeather.forecastWeatherDataResult=result;


        touchEnabled=true;
       // mainActivity.doAction(result);
    }
}