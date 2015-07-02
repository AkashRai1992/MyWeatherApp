package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akki on 20/4/15.
 */
public class SunriseSunset implements Serializable {
    private static final long serialVersionUID = 36L;

    @SerializedName("country")
    public String CountryName;

    @SerializedName("sunrise")
    public  long  Sunrise;

    @SerializedName("sunset")
    public  long Sunset;


    @Override
    public String toString() {
        return "SunriseSunset{" +
                "CountryName='" + CountryName + '\'' +
                ", Sunrise=" + Sunrise +
                ", Sunset=" + Sunset +
                '}';
    }
}
