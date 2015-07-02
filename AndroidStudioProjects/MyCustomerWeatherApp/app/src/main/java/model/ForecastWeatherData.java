package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by akki on 17/4/15.
 */
public class ForecastWeatherData implements Serializable {

    private static final long serialVersionUID = 48L;

    @SerializedName("city")
     public Citi CityName;
    @SerializedName("country")
     public String CountryName;

    @SerializedName("list")
     public  ArrayList<TempVariables> temp_var;


    @Override
    public String toString() {
        return "ForecastWeatherData{" +
                "CityName=" + CityName +
                ", CountryName='" + CountryName + '\'' +
                ", temp_var=" + temp_var +
                '}';
    }
}
