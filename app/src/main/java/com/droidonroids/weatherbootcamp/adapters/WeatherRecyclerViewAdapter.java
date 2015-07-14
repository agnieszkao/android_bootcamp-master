package com.droidonroids.weatherbootcamp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidonroids.weatherbootcamp.R;
import com.droidonroids.weatherbootcamp.data.network.entities.Main;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import com.droidonroids.weatherbootcamp.utils.Constants;
import com.droidonroids.weatherbootcamp.utils.UrlBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter {

    private ArrayList<WeatherResponse> mWeathers;
    private Context mContext;
    private View mConvertView;

    public WeatherRecyclerViewAdapter(Context context, ArrayList<WeatherResponse> weathers){
        mContext = context;
        mWeathers = weathers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item_weather, parent, false);
        return new RecyclerWeatherBigItemHolder(mConvertView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        WeatherResponse weatherResponse = mWeathers.get(position);

        switch(itemType) {
            case Constants.ITEM_TYPE_BIG_WEATHER:
                setData(holder, weatherResponse);
                break;
        }
    }

    private void setData(RecyclerView.ViewHolder holder, WeatherResponse weatherResponse) {
        RecyclerWeatherBigItemHolder bigItemHolder = (RecyclerWeatherBigItemHolder) holder;

        String weatherIcon = weatherResponse.getWeathers().get(0).getIcon();
        if(weatherIcon != null) {
            String imageUrl = UrlBuilder.getImageUrl(weatherIcon);
            Picasso.with(mContext).load(imageUrl).into(bigItemHolder.imageIconImgView);
        }

        String description = weatherResponse.getWeathers().get(0).getDescription();
        bigItemHolder.descriptionTxtView.setText(description);

        Main main = weatherResponse.getMain();
        if(main != null) {
            Double temp = main.getTemp();
            bigItemHolder.temperatureTxtView.setText(temp + "\u00b0" + "C");
        }
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }

    @Override
    public int getItemViewType(int position) {
        return Constants.ITEM_TYPE_BIG_WEATHER;
    }

    public static class RecyclerWeatherBigItemHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.weather_icon)
        ImageView imageIconImgView;
        @Bind(R.id.description)
        TextView descriptionTxtView;
        @Bind(R.id.temperature)
        TextView temperatureTxtView;

        RecyclerWeatherBigItemHolder(View view) {
            super(view);
            ButterKnife.bind(view);
        }
    }
}
