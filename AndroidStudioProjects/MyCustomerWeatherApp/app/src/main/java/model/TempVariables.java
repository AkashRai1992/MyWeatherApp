package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by akki on 17/4/15.
 */
public class TempVariables implements Serializable {
    private static final long serialVersionUID = 44L;


    @SerializedName("humidity")
    public float humidity;

    @SerializedName("weather")
    public ArrayList<WeatherVariables> weatherVariables_forecast;

    @SerializedName("temp")
    public ForecastTempDetails forecastTempDetails;

    @Override
    public String toString() {
        return "TempVariables{" +
                "humidity=" + humidity +
                '}';
    }
}
