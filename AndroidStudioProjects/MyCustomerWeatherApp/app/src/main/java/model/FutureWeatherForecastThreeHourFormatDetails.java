package model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akki on 28/4/15.
 */
public class FutureWeatherForecastThreeHourFormatDetails {

    private static final long serialVersionUID = 9L;
    @SerializedName("dt")
    public long time;
    @SerializedName("main")
    public Temperature temperature;
    @SerializedName("weather")
    public ArrayList<WeatherVariables> weather_var;

    @Override
    public String toString() {
        return "FutureWeatherForecastThreeHourFormatDetails{" +
                "time=" + time +
                ", temperature=" + temperature +
                ", weather_var=" + weather_var +
                '}';
    }
}
