package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akki on 21/4/15.
 */
public class WeatherVariables implements Serializable {
    private static final long serialVersionUID = 44L;
    @SerializedName("id")
        public float weather_condition_id ;
    @SerializedName("main")
       public String group_of_weather_parameters;
    @SerializedName("description")
    public  String weather_desc;
    @SerializedName("icon")
     public String weather_icon_id;

    @Override
    public String toString() {
        return "WeatherVariables{" +
                "weather_condition_id=" + weather_condition_id +
                ", group_of_weather_parameters='" + group_of_weather_parameters + '\'' +
                ", weather_desc='" + weather_desc + '\'' +
                ", weather_icon_id='" + weather_icon_id + '\'' +
                '}';
    }
}
