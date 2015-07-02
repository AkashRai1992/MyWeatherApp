package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akki on 20/4/15.
 */
public class WindAtr implements Serializable {
    private static final long serialVersionUID = 30L;
    @SerializedName("speed")
    public float windSpeed;
    @SerializedName("deg")
    public float dewpoint;

    @Override
    public String toString() {
        return "WindAtr{" +
                "windSpeed=" + windSpeed +
                ", degree=" + dewpoint +
                '}';
    }
}
