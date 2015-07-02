package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akki on 23/4/15.
 */
public class ForecastTempDetails implements Serializable {

    private static final long serialVersionUID = 24L;

    @SerializedName("day")
    public float avg_temp;


}
