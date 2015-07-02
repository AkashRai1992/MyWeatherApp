package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akki on 17/4/15.
 */
public class Temperature implements Serializable {

    private static final long serialVersionUID = 42L;
    @SerializedName("temp")
    public float temp_avg;

    @SerializedName("temp_min")
    public float temp_min;

    @SerializedName("temp_max")
    public float temp_max;

    @SerializedName("pressure")
    public float pressure;

    @SerializedName("sea_level")
    public float sea_level;

    @SerializedName("grnd_level")
    public float groundLevel;

    @SerializedName("humidity")
    public float humidity;

    @Override
    public String toString() {
        return "Temperature{" +
                "temp_avg=" + temp_avg +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", sea_level=" + sea_level +
                ", groundLevel=" + groundLevel +
                ", humidity=" + humidity +
                '}';
    }
}
