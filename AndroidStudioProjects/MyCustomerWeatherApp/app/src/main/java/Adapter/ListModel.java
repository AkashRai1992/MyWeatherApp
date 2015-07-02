package Adapter;

/**
 * Created by akki on 28/4/15.
 */
public class ListModel {
    private  int temp;
    private String weather_icon_id;
    private String currentTime;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getWeather_icon_id() {
        return weather_icon_id;
    }

    public void setWeather_icon_id(String weather_icon_id) {
        this.weather_icon_id = weather_icon_id;
    }
}
