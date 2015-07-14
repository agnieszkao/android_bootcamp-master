package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ForecastResponse {
	@SerializedName("list")
	private ArrayList<WeatherResponse> mWeatherResponses;
    @SerializedName("city")
    private City mCity;

	public ArrayList<WeatherResponse> getWeatherResponses() {
		return this.mWeatherResponses;
	}

	public void setWeatherResponses(ArrayList<WeatherResponse> weatherResponses) {
		this.mWeatherResponses = weatherResponses;
	}

    public City getCity() {
        return mCity;
    }

    public void setCity(City city) {
        this.mCity = city;
    }

	@Override public String toString() {
		return "ForecastResponse{" +
			"WeatherResponses=" + mWeatherResponses +
			'}';
	}
}
