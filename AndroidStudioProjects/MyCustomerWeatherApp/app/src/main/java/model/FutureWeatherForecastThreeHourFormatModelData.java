package model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akki on 28/4/15.
 */
public class FutureWeatherForecastThreeHourFormatModelData {

    private static final long serialVersionUID = 19L;
    @SerializedName("list")
    public   ArrayList<FutureWeatherForecastThreeHourFormatDetails>   futureWeatherForecastThreeHourFormatDetails;

    @Override
    public String toString() {
        return "FutureWeatherForecastThreeHourFormatModelData{" +
                "futureWeatherForecastThreeHourFormatDetails=" + futureWeatherForecastThreeHourFormatDetails +
                '}';
    }
}
