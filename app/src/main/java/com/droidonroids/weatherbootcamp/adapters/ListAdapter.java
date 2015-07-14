package com.droidonroids.weatherbootcamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidonroids.weatherbootcamp.R;
import com.droidonroids.weatherbootcamp.data.network.entities.Main;
import com.droidonroids.weatherbootcamp.data.network.entities.Weather;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import com.droidonroids.weatherbootcamp.utils.UrlBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListAdapter extends ArrayAdapter<WeatherResponse> {

    private LayoutInflater mLayoutInflater;
    public ArrayList<WeatherResponse> weatherResponses = null;
    private Context context;

    public ListAdapter(Context context, ArrayList<WeatherResponse> weatherResponses) {
        super(context, R.layout.lv_item_weather, weatherResponses);
        this.context = context;
        this.weatherResponses = weatherResponses;
        mLayoutInflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WeatherViewHolder holder;

        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.lv_item_weather, parent, false);

            holder = new WeatherViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (WeatherViewHolder)convertView.getTag();
        }

        WeatherResponse weatherResponse = getItem(position);

        ArrayList<Weather> weathers = weatherResponse.getWeathers();

        if(weathers != null && weathers.size() > 0) {
            Weather weather = weathers.get(0);
            String weathersDescriptions = weather.getDescription();
            holder.descriptionTxtView.setText(weathersDescriptions);
            String weatherIcon = weather.getIcon();
            if(weatherIcon != null) {
                String imageUrl = UrlBuilder.getImageUrl(weatherIcon);
                Picasso.with(context).load(imageUrl).into(holder.imageIconImgView);
            }
        }
        else {
            holder.descriptionTxtView.setText("Brak danych");
        }

        Main main = weatherResponse.getMain();
        if(main != null) {
            Double temp = main.getTemp();
            holder.temperatureTxtView.setText(temp + "\u00b0" + "C");
        }

        String time = weatherResponse.getTime();
        if(time != null) {
            holder.timeTxtView.setText(time.substring(0,16));
        }

        return convertView;
    }

    static class WeatherViewHolder {
        @Bind(R.id.weather_icon)
        ImageView imageIconImgView;
        @Bind(R.id.description)
        TextView descriptionTxtView;
        @Bind(R.id.temperature)
        TextView temperatureTxtView;
        @Bind(R.id.time)
        TextView timeTxtView;

        WeatherViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
