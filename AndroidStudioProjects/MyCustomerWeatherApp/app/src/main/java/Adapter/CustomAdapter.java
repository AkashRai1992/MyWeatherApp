package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akki.mycustomerweatherapp.R;
import com.example.akki.mycustomerweatherapp.ThreeHourFormatActivity;

import Calander.DateView;
import model.FutureWeatherForecastThreeHourFormatDetails;
import model.FutureWeatherForecastThreeHourFormatModelData;

/**
 * Created by akki on 28/4/15.
 */
public class CustomAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return ThreeHourFormatActivity.getCount();
    }

    @Override
    public FutureWeatherForecastThreeHourFormatDetails getItem(int position) {
        return  ThreeHourFormatActivity.futureWeatherForecastThreeHourFormatModelData.futureWeatherForecastThreeHourFormatDetails.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ThreeHourFormatActivity.context);
            convertView = inflater.inflate(R.layout.list_view_layout, parent, false);
        }

        TextView time_stamp = (TextView) convertView.findViewById(R.id.textview_time_stamp);
        TextView temp = (TextView) convertView.findViewById(R.id.textview_temp);
        ImageView weather_icon=(ImageView)convertView.findViewById(R.id.image_view_weather_icon_three_hour_format);

        FutureWeatherForecastThreeHourFormatDetails adapterFutureforecast = ThreeHourFormatActivity.futureWeatherForecastThreeHourFormatModelData.futureWeatherForecastThreeHourFormatDetails.get(position);

        time_stamp.setText(DateView.getDateCurrentTimeZone(adapterFutureforecast.time));
        temp.setText(String.valueOf(adapterFutureforecast.temperature.temp_avg)+(char) 0x00B0 + "C");
      ThreeHourFormatActivity.threeHourFormatActivtiy.setWeatherIcon(position,weather_icon);


        return convertView;
    }


}





