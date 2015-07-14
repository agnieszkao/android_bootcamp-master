package com.droidonroids.weatherbootcamp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidonroids.weatherbootcamp.R;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import com.droidonroids.weatherbootcamp.utils.Constants;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter {

    private ArrayList<WeatherResponse> mWeathers;

    public WeatherRecyclerViewAdapter(ArrayList<WeatherResponse> weathers){
        mWeathers = weathers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        WeatherResponse weatherResponse = mWeathers.get(position);

        switch(itemType) {
            case Constants.ITEM_TYPE_BIG_WEATHER:
                RecyclerWeatherBigItemHolder bigItemHolder = (RecyclerWeatherBigItemHolder) holder;

                break;
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
