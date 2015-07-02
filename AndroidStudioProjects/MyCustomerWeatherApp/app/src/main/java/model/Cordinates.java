package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akki on 20/4/15.
 */
public class Cordinates implements Serializable {
    private static final long serialVersionUID = 40L;

    @SerializedName("lon")
    public float longitude;

    @SerializedName("lat")
    public float latitude;

    @Override
    public String toString() {
        return "Cordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
