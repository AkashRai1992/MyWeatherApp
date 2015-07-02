package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by akki on 17/4/15.
 */
public class CurrentWeatherData implements Serializable {


    private static final long serialVersionUID = 46L;

    @SerializedName("name")
    public String CoutryName;

    @SerializedName("main")
    public Temperature temperature;

    @SerializedName("coord")
    public Cordinates cordinates;

    @SerializedName("sys")
    public SunriseSunset sunriseSunset;
    @SerializedName("wind")
    public  WindAtr windAtr;
    @SerializedName("weather")
    public ArrayList<WeatherVariables> weather_var;
    @SerializedName("dt")
    public long last_updated_time;
    @Override
    public String toString() {
        return "CurrentWeatherData{" +
                "CoutryName='" + CoutryName + '\'' +
                ", temperature=" + temperature +
                ", cordinates=" + cordinates +
                ", sunriseSunset=" + sunriseSunset +
                '}';
    }
}





