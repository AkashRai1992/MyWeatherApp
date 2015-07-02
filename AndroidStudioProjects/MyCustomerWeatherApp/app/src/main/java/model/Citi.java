package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akki on 17/4/15.
 */
public class Citi implements Serializable {
    private static final long serialVersionUID = 49L;
    @SerializedName("name")
    public String citiname;
}
